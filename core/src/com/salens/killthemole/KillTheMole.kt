package com.salens.killthemole

/**
 * Created by Antropov Igor on 15.11.2015.
 */
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import javafx.scene.layout.Background
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.screens.MainMenuScreen
import com.salens.killthemole.screens.PlayScreen

class KillTheMole : Game() {

    public var font : BitmapFont? = null
    var levels: BitmapFont? = null

    override fun create() {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("data/fonts/alpha_echo.ttf"))
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.size = Gdx.graphics.height / 10
        param.characters = FONT_CHARACTERS
        font = generator.generateFont(param)
        param.size = Gdx.graphics.height / 10
        levels = generator.generateFont(param)
        font?.color = Color.WHITE
        levels?.color = Color.WHITE
        generator.dispose()
        val assets = AssetsLoader.getInstance()
        assets.load()
        setScreen(MainMenuScreen(this))
    }

    override fun render() {
       super.render()
    }
    companion object {
        private val FONT_CHARACTERS = "àáâãäå¸æçèéêëìíîïğñòóôõö÷øùúûüışÿÀÁÂÃÄÅ¨ÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"?`'<>";
    }
}
