package com.salens.killthemole.helpers

/**
 * Created by Antropov Igor on 19.11.2015.
 */
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Music
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
    public var mainbackground: Texture? = null
    public var grasstop: Texture? = null
    public var music: Music? = null
    public var sound1: Sound? = null
    public var sound2: Sound? = null


    companion object {
        private var _instance: AssetsLoader = AssetsLoader()
        fun getInstance(): AssetsLoader = _instance
        public val prefs = Gdx.app.getPreferences("KillTheMole")
    }

    public fun load() {
        loadWeapons()
        if (!prefs.contains("Coins")) {prefs.putInteger("Coins", 0); prefs.flush()}
        moleAlive = Texture(Gdx.files.internal("data/images/mole.png"))
        moleDead = Texture(Gdx.files.internal("data/images/hole.png"))
        background = Texture(Gdx.files.internal("data/images/grass.png"))
        test = Texture(Gdx.files.internal("data/badlogic.jpg"))
        mainbackground = Texture(Gdx.files.internal("data/images/mole2.jpg"))
        grasstop = Texture(Gdx.files.internal("data/images/grass_top.png"))
        music = Gdx.audio.newMusic(Gdx.files.internal("data/music/music1.ogg"))
        sound1 = Gdx.audio.newSound(Gdx.files.internal("data/music/sound1.wav"))
        sound2 = Gdx.audio.newSound(Gdx.files.internal("data/music/sound2.mp3"))
    }

    public fun getPrefs(): Preferences = prefs

    private fun loadWeapons() {
        if (!prefs.contains("HammerDamage")) {prefs.putInteger("HammerDamage", 20); prefs.flush()}
        if (!prefs.contains("HammerLevel"))  {prefs.putInteger("HammerLevel", 1  ); prefs.flush()}
        if (!prefs.contains("ShovelDamage")) {prefs.putInteger("ShovelDamage", 10); prefs.flush()}
        if (!prefs.contains("ShovelLevel"))  {prefs.putInteger("ShovelLevel", 1) ;  prefs.flush()}
    }
}