package com.salens.killthemole.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.salens.killthemole.KillTheMole
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.helpers.XMLparse
import com.salens.killthemole.objects.Background

class LevelScreen(internal val game: KillTheMole, weapon: String) : Screen {

    private val stage: Stage
    private val skin: Skin
    private val levels: Array<String>
    private val table: Table
    private val labelStyle: Label.LabelStyle
    private var level: TextButton? = null
    private val pixmap: Pixmap
    private val background: Background


    init {
        stage = Stage(ScreenViewport())
        skin = Skin()
        skin.add("default", game.levels)
        pixmap = Pixmap((Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        skin.add("background", Texture(pixmap))
        background = Background()
        background.setPosition(0f, 0f)
        stage.addActor(background)

        val textButtonStyle = TextButton.TextButtonStyle()
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY)
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY)
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY)
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY)
        textButtonStyle.font = skin.getFont("default")
        skin.add("default", textButtonStyle)

        val parseLevels = XMLparse()
        levels = parseLevels.XMLparseLevels()
        labelStyle = Label.LabelStyle()
        labelStyle.font = game.levels
        table = Table()
        table.row().pad(20f).width(200f).height(50f)
        table.center()
        table.setFillParent(true)

        for(i in 0..levels.size - 1) {
            val cur_level = levels[i]
            level = TextButton(cur_level + " level", textButtonStyle)
            level?.addListener(object : ClickListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    return true
                }

                override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                    game.screen = PlayScreen(cur_level, game, weapon)
                    dispose()
                }
            })
            table.add(level)
            val indexLevel = java.lang.Float.parseFloat(i.toString()) + 1
            if (indexLevel % 5.0f != 0f) table.row().pad(20f).width(200f).height(50f) else table.columns
        }
        stage.addActor(table)

        Gdx.input.inputProcessor = stage
        Gdx.input.isCatchBackKey = true

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        stage.dispose()
        game.dispose()
    }
}