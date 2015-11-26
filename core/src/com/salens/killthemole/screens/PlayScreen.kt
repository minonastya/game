package com.salens.killthemole.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.salens.killthemole.KillTheMole
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Background
import com.salens.killthemole.objects.Level
import com.salens.killthemole.objects.Mole
import com.salens.killthemole.objects.weapons.Weapon

/**
 * Created by Antropov Igor on 14.11.2015.
 */

public class PlayScreen(val numOfLevel: String, val game: KillTheMole, weapon: String) : Screen {

    private val level: Level
    private val batch: Batch
    private val stage: Stage
    private val backgroung: Background

   // private val game : KillTheMole

    init {
       // this.game = game
        level = Level(numOfLevel, weapon)
        batch = SpriteBatch()
        stage = Stage()
        backgroung = Background()
        backgroung.setPosition(0f, 0f)
        stage.addActor(backgroung)
        for (mole in level.molesArray)
            stage.addActor(mole)



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

    override fun render(delta: Float) {
        level.update(delta)
        if (level.isGameOver) {
            level.gameOver()
            game.setScreen(MainMenuScreen(game))
            dispose()
        } else {
            stage.act(delta)
            stage.draw()
        }

    }

    override fun resume() {

    }

    override fun dispose() {
        stage.dispose()
        //game.dispose()
    }

}