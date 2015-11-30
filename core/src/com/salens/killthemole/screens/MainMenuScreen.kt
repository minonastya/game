package com.salens.killthemole.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.salens.killthemole.KillTheMole
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Background

class MainMenuScreen(val game: KillTheMole): Screen {
    private val stage: Stage
    private val skin: Skin
    private val label: Label
    private val play: TextButton
    private val settings: TextButton
    private val exit: TextButton
    private val table: Table
    private val assets = AssetsLoader.getInstance()
    private val music: Music?
    private val background: Background

    init {
        music = assets.music
        music?.play()
        music?.setVolume(1f)
        music?.setLooping(true)
        stage = Stage(ScreenViewport())
        background = Background()
        background.setPosition(0f, 0f)
        stage.addActor(background)
        skin = Skin()
        skin.add("default", game.levels)
        skin.add("ButtonOn", assets.buttonon)
        skin.add("ButtonOff", assets.buttonoff)
        val labelStyle = Label.LabelStyle()
        labelStyle.font = game.font
        labelStyle.fontColor = Color.WHITE
        label = Label("KILL THE MOLE", labelStyle)
        label.setAlignment(Align.center)

        val textButtonStyle = TextButton.TextButtonStyle()
        textButtonStyle.up = skin.newDrawable("ButtonOn")
        textButtonStyle.down = skin.newDrawable("ButtonOff")
        textButtonStyle.checked = skin.newDrawable("ButtonOn")
        textButtonStyle.over = skin.newDrawable("ButtonOff")
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

        settings = TextButton("Settings", skin)

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
        table.add(label).width(400f).center()
        table.row().height(100f)
        table.add(play).width(400f)
        table.row().height(100f)
        table.add(settings).width(400f)
        table.row().height(100f)
        table.add(exit).width(400f)
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