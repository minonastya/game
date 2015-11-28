package com.salens.killthemole.objects


/**
 * Created by Antropov Igor on 19.11.2015.
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.salens.killthemole.helpers.AssetsLoader

class Background:Actor() {

    private val backgroundTexture:Texture
    private val backgroundSprite:Sprite
    private val assets = AssetsLoader.getInstance()

    init{
        backgroundTexture = assets.grasstop ?:throw NullPointerException()
        backgroundSprite = Sprite(backgroundTexture)
        backgroundSprite.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }
    override fun draw(batch:Batch, alpha:Float) {
        backgroundSprite.draw(batch)
    }
}