package com.salens.killthemole

/**
 * Created by Antropov Igor on 15.11.2015.
 */
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import javafx.scene.layout.Background
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.screens.PlayScreen

class KillTheMole : Game() {


    override fun create() {
        val assets = AssetsLoader.getInstance()
        assets.load()
        setScreen(PlayScreen("1", this))
    }

    override fun render() {
       super.render()
    }
}
