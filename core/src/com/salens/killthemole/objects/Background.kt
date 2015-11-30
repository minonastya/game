package com.salens.killthemole.objects


/**
 * Created by Antropov Igor on 19.11.2015.
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.salens.killthemole.helpers.AssetsLoader

class Background: Actor() {
    private val backgroundTexture: Texture?
    private val backgroundSprite: Sprite
    private val mushroomr: Texture?
    private val mushroomrSprite: Sprite
    private val mushroomb: Texture?
    private val mushroombSprite: Sprite
    private val mushroomw: Texture?
    private val mushroomwSprite: Sprite
    private val pine: Texture?
    private val pineSprite: Sprite?
    private val oak: Texture?
    private val oakSprite: Sprite?
    private val assets = AssetsLoader.getInstance()
    val screenWidth = Gdx.graphics.getWidth()
    val screenHeight = Gdx.graphics.getHeight()
    init{
        backgroundTexture = assets.background
        backgroundSprite = Sprite(backgroundTexture,screenWidth,screenHeight)
        mushroomr = assets.mushroomred
        mushroomrSprite = Sprite(mushroomr)
        mushroomb = assets.mushroombrown
        mushroombSprite = Sprite(mushroomb)
        mushroomw = assets.mushroomwhite
        mushroomwSprite = Sprite(mushroomw)
        pine = assets.pine
        pineSprite = Sprite(pine)
        oak = assets.oak
        oakSprite = Sprite(oak)
    }
    override fun draw(batch:Batch, alpha:Float) {
        backgroundSprite.draw(batch)
        var x = -75f
        while (x < screenWidth){
            mushroomrSprite.draw(batch)
            mushroomrSprite.setPosition(x + 50f, 65f)
            x += 50f
            mushroombSprite.draw(batch)
            mushroombSprite.setPosition(x + 50f, 65f)
            x += 50f
            mushroomwSprite.draw(batch)
            mushroomwSprite.setPosition(x + 50f, 65f)
            x += 50f
        }
        var y = screenHeight
        while (y - pine!!.height > 100f) {
            y -= pine.height
            pineSprite?.setPosition(20f, y.toFloat())
            pineSprite?.draw(batch)
            oakSprite?.setPosition(screenWidth - assets.oak!!.width - 20f, y.toFloat())
            oakSprite?.draw(batch)
        }
    }
}