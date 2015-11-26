package com.salens.killthemole.screens

/**
 * Created by Antropov Igor on 26.11.2015.
 */
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.Screen
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
import com.salens.killthemole.objects.Coins

public class ShopScreen(game: KillTheMole) : Screen {
    private val stage: Stage
    private val skin: Skin
    private val hammer: TextButton
    private val shovel: TextButton
    private val shovelLevelUp: TextButton
    private val hammerLevelUp: TextButton
    private val table: Table
    private val pixmap: Pixmap

    private val play: TextButton

    private val table2: Table

    private var weaponType: String = ""
    private val pref: Preferences = Gdx.app.getPreferences("KillTheMole")
    private val coins = Coins()

    init {
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

        val textButtonStyle2 = TextButton.TextButtonStyle()
        textButtonStyle2.up = skin.newDrawable("background", Color.GRAY)
        textButtonStyle2.down = skin.newDrawable("background", Color.DARK_GRAY)
        textButtonStyle2.checked = skin.newDrawable("background", Color.GRAY)
        textButtonStyle2.over = skin.newDrawable("background", Color.LIGHT_GRAY)
        textButtonStyle2.font = skin.getFont("default")
        skin.add("withChecked", textButtonStyle2)

        table = Table()
        table.setFillParent(true)

        hammer = TextButton("Hammer", skin)
        hammer.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                weaponType = "Hammer"

            }
        })

        shovel = TextButton("Shovel", skin)
        shovel.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                weaponType = "Shovel"

            }
        })
        hammerLevelUp = TextButton("LevelUp", skin, "withChecked")
        hammerLevelUp.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                val curLev = pref.getInteger("HammerLevel")
                if (coins.spendCoins((Math.pow(curLev.toDouble(), 2.0)).toInt())) {
                    pref.putInteger("HammerLevel", curLev + 1)
                    Gdx.app.log("Level", "Upped")
                    pref.flush()
                }
            }
        })

        shovelLevelUp = TextButton("LevelUp", skin, "withChecked")
        shovelLevelUp.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                val curLev = pref.getInteger("ShovelLevel")
                if (coins.spendCoins((Math.pow(curLev.toDouble(), 3.0)).toInt())) {
                    pref.putInteger("ShovelLevel", curLev + 1)
                    Gdx.app.log("Level123", "Upped")
                    pref.flush()
                }
            }
        })
        table.row().height(100f)
        table.add(hammer).width(300f)
        table.add(hammerLevelUp).width(200f)
        table.row().height(100f)
        table.add(shovel).width(300f)
        table.add(shovelLevelUp).width(200f)
        table.left()
        stage.addActor(table)



        table2 = Table()
        table2.setFillParent(true)

        play = TextButton("GO", skin)
        play.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(PlayScreen("1", game, weaponType))
                dispose()
            }
        })

        table2.row().height(100f)
        table2.add(play).width(100f)
        table2.bottom().right()
        stage.addActor(table2)

        Gdx.input.setInputProcessor(stage)
        Gdx.input.setCatchBackKey(true)
    }


    public override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    public override fun resize(width: Int, height: Int) {
    }

    public override fun show() {
    }

    public override fun hide() {
    }

    public override fun pause() {
    }

    public override fun resume() {
    }

    public override fun dispose() {
        stage.dispose()
    }
}