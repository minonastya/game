package com.salens.killthemole.objects

/**
 * Created by Antropov Igor on 18.11.2015.
 */
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.salens.killthemole.helpers.XMLparse
import java.util.*

public class Level(val level: String, weapon: String) {

    public var isGameOver = false
    //public var isGamePaused = false
    public var molesArray: Array<Mole>
    public var delay: Long = 1
    private val random = Random()
    //private var time : Float = 0f
    private var timer: Timer
    //private var myTimerTask : TimerTask = setMoleAlive(Random())
    public val player: Player
    private val coins: Coins



    init {
        player = Player(weapon)
        coins = Coins()

        val xml = XMLparse()
        val posArray = xml.getPos(level)

        delay = xml.getDelay()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                setMoleAlive(random)
            }
        }, delay, delay)

        molesArray = Array<Mole>(posArray.size,
                { i -> Mole(posArray[i].first.toFloat(), posArray[i].second.toFloat(),
                        player, xml.getHealth(), xml.getAttack())})

        var i = 0
        for (mole in molesArray) {
            mole.setBounds(
                    Gdx.graphics.width * posArray[i].first.toFloat() / 100,
                    Gdx.graphics.height * posArray[i].second.toFloat() / 100 - mole.getImg().height / 2,
                    mole.getImg().width,
                    mole.getImg().height
            )
            i++
        }
    }

    public fun update(delta: Float): Int {
        if (player.health < 1) isGameOver = true
        for (mole in molesArray) mole.update(delta)
        return player.moleKilled
    }

    public fun gameOver() {
        timer.cancel()
        for (mole in molesArray) {
            mole.Timer = Timer()
        }
        Gdx.app.log("Score", player.moleKilled.toString())
        coins.addCoins(player.moleKilled)
    }

    public fun pause(){
        timer.cancel()
        for(mole in molesArray){
            mole.Timer.cancel()
        }
    }

    public fun continueFromPause(){
        timer = Timer()
        for(mole in molesArray){
            mole.Timer = Timer()
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                setMoleAlive(random)
            }
        }, delay, delay)
    }

    private fun setMoleAlive(rand: Random) {
        val num = rand.nextInt(molesArray.size - 1)
        if (molesArray[num].isDead) {
            molesArray[num].resurrect()
        }
    }
}

