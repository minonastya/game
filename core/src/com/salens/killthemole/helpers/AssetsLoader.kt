package com.salens.killthemole.helpers

/**
 * Created by Antropov Igor on 19.11.2015.
 */
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion


public class AssetsLoader {



    public var moleAlive: Texture? = null
    public var moleDead: Texture? = null
    public var background: Texture? = null
    public var test: Texture? = null

    companion object {
        private var _instance: AssetsLoader = AssetsLoader()
        fun getInstance(): AssetsLoader = _instance
        public val prefs = Gdx.app.getPreferences("KillTheMole")
    }

    public fun load() {
        loadWeapons()
        moleAlive = Texture(Gdx.files.internal("data/images/mole.png"))
        moleDead = Texture(Gdx.files.internal("data/images/hole.png"))
        background = Texture(Gdx.files.internal("data/images/grass.png"))
        test = Texture(Gdx.files.internal("data/badlogic.jpg"))
    }


    public fun getPrefs(): Preferences = prefs

    private fun loadWeapons() {
        if (!prefs.contains("HammerDamage")) prefs.putInteger("HammerDamage", 2)
        if (!prefs.contains("HammerLevel")) prefs.putInteger("HammerLevel", 1)
    }
}