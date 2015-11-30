package com.salens.killthemole.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.salens.killthemole.KillTheMole
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Background
import com.salens.killthemole.objects.Level


/**
 * Created by Antropov Igor on 14.11.2015.
 */

public class PlayScreen(val numOfLevel: String, val game: KillTheMole, val weapon: String) : Screen {

    private val level: Level
    private val batch: Batch
    private val stage: Stage
    private val background: Background
    private var flag: Boolean
    private var labelStyle: Label.LabelStyle
    private var scoreLabel: Label
    private val scoreTable: Table
    private var currentAmountDeath: Int
    private val assets = AssetsLoader.getInstance()
    private val sound2: Sound?
    private val music: Music?

    init {
        music = assets.music
        music?.play()
        music?.setVolume(0.5f)
        sound2 = assets.sound2
        currentAmountDeath = 0
        labelStyle = Label.LabelStyle()
        labelStyle.font = game.font

        scoreLabel = Label("Score: $currentAmountDeath", labelStyle)
        scoreTable = Table()
        scoreTable.setFillParent(true)
        scoreTable.add(scoreLabel)
        scoreTable.bottom().left()

        level = Level(numOfLevel, weapon)
        flag = true

        batch = SpriteBatch()
        stage = Stage()
        background = Background()
        background.setPosition(0f, 0f)
        stage.addActor(background)
        for (mole in level.molesArray)
            stage.addActor(mole)


        stage.addActor(scoreTable)
        Gdx.input.inputProcessor = stage
    }


    override fun show() {
    }

    override fun pause() {
    }

    override fun hide() {

    }

    override fun resize(width: Int, height: Int) {

    }

    private fun endGameActor(): Table {
        music?.pause()
        sound2?.play()
        val skin = Skin()
        skin.add("default", game.levels)
        val pixmap = Pixmap((Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        skin.add("background", Texture(pixmap))

        val textButtonStyle = TextButton.TextButtonStyle()
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY)
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY)
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY)
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY)
        textButtonStyle.font = skin.getFont("default")
        skin.add("default", textButtonStyle)

        val table = Table()
        table.setFillParent(true)

        val retry = TextButton("retry", skin)
        retry.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = PlayScreen(numOfLevel, game, weapon)
                sound2?.pause()
                dispose()
            }
        })
        val mainMenu = TextButton("Main menu", skin)
        mainMenu.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = MainMenuScreen(game)
                sound2?.stop()
                dispose()
            }
        })
        val score = Label("Your score: ${level.player.moleKilled} ", labelStyle)
        val money = Label("Your money: ${level.player.moleKilled * numOfLevel.toInt()}", labelStyle)

        table.add(score)
        table.row()
        table.add(money)
        table.row()
        table.add(retry)//.width(100f)
        table.add(mainMenu)//.width(100f)
        table.center()
        table.setFillParent(true)
        return table
    }

    override fun render(delta: Float) {

        if (flag) {
            if (level.isGameOver) {
                level.gameOver()
                stage.addActor(endGameActor())
                flag = false
            } else {
                currentAmountDeath = level.update(delta)
                scoreLabel.setText("Score: $currentAmountDeath")
                stage.act(delta)
                stage.draw()
            }
        } else {
            stage.act(delta)
            stage.draw()
        }

    }

    override fun resume() {

    }

    override fun dispose() {
        stage.dispose()
        game.dispose()
    }

}