package com.salens.killthemole.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.salens.killthemole.KillTheMole
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.salens.killthemole.helpers.AssetsLoader

class MainMenuScreen(val game: KillTheMole): Screen {
    private val stage: Stage
    private val skin: Skin
    private val play: TextButton
    private val exit: TextButton
    private val table: Table
    private val pixmap: Pixmap
    private val assets = AssetsLoader.getInstance()
    private val music: Music?

    init {
        music = assets.music
        music?.play()
        music?.setVolume(1f)
        music?.setLooping(true)
        stage = Stage(ScreenViewport())
        skin = Skin()
        skin.add("default", game.levels)
        pixmap = Pixmap((Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888)
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

        table = Table()
        table.setFillParent(true)

        play = TextButton("Play", skin)
        play.addListener(object: ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int):Boolean {
                return true;
            }
            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(ShopScreen(game))
            }
        })


        exit = TextButton("Exit", skin)
        exit.addListener(object: ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int):Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.exit()
                dispose()
            }
        })
        table.row().height(100f)
        table.add(play).width(400f)
        table.row().height(100f)
        table.add(exit).width((400f))
        stage.addActor(table)

        Gdx.input.setInputProcessor(stage)
        Gdx.input.setCatchBackKey(true)
    }


    public override fun render(delta: Float)
    {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    public override fun resize(width: Int, height: Int) {
    }

    public override fun show(){
    }

    public override fun hide(){
    }

    public override fun pause() {
    }

    public override fun resume(){
    }

    public override fun dispose(){
        stage.dispose()
    }
}