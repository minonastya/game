package com.salens.killthemole.objects


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.salens.killthemole.helpers.AssetsLoader
import java.util.*


/**
 * Created by Antropov Igor on 14.11.2015.
 */

public class Mole(val ourX: Float, val ourY: Float, val player: Player, val health: Int, val damage: Int) : Actor() {


    // private level : Level
    private var img: Sprite
    private var imgAlive: Sprite
    private var imgAlive_texture: Texture?
    private var imgDead: Sprite
    private var imgDead_texture: Texture?
    private val assets = AssetsLoader.getInstance()
    private var Timer: Timer = Timer()

    private var currentHealth: Int

    public var isDead: Boolean = true


    init {
        //deadCounter = 0
        currentHealth = health
        imgDead_texture = assets.moleDead ?: throw NullPointerException("moleDead texture")
        imgDead = Sprite(imgDead_texture)
        imgDead.setSize(Gdx.graphics.height.toFloat() * 15 / 100, Gdx.graphics.height.toFloat() * 15 / 100)
        imgAlive_texture = assets.moleAlive ?: throw NullPointerException("moleAlive texture")
        imgAlive = Sprite(imgAlive_texture)
        imgAlive.setSize(Gdx.graphics.height.toFloat() * 15 / 100, Gdx.graphics.height.toFloat() * 15 / 100)
        img = imgDead

        //this.sound = Gdx.audio.newSound(Gdx.files.internal("sounds/aaa.mp3"))
        addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                currentHealth -= player.attack()

                Gdx.app.log("CorrentHealthOfMole", "$currentHealth")
                return true
            }
        })
        setTouchable(Touchable.disabled)
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width, height)
        this.imgAlive.setPosition(x, y)
        this.imgDead.setPosition(x, y)
    }

    fun escaping() {
        isDead = true
        player.getHurted(damage)
    }

    override fun draw(batch: Batch, alpha: Float) {
        if (isDead)
            this.imgDead.draw(batch)
        else
            this.imgAlive.draw(batch)
    }

    public fun update(delta: Float) {
        if (currentHealth < 1) {
            currentHealth = health
            isDead = true
            player.moleKilled++
            setTouchable(Touchable.disabled)
            Timer.cancel()
            img = imgDead
        }
    }

    public fun resurrect() {
        isDead = false
        setTouchable(Touchable.enabled)
        Timer = Timer()
        Timer.schedule(object : TimerTask() {
            override fun run() {
                escaping()
            }
        }, 2500)

    }

    public fun getImg(): Sprite {
        return this.imgAlive
    }
}