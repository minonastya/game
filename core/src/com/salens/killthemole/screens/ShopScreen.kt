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
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Background
import com.salens.killthemole.objects.Coins

public class ShopScreen(game: KillTheMole) : Screen {
    private val stage: Stage
    private val skin: Skin
    private val hammer: TextButton
    private val shovel: TextButton
    private val shovelLevelUp: TextButton
    private val hammerLevelUp: TextButton
    private val table: Table
    private val moneyTable: Table
    private val play: TextButton
    private val table2: Table
    private val moneyLabel: Label
    private val background: Background
    private var weaponType: String = ""
    private val pref: Preferences = Gdx.app.getPreferences("KillTheMole")
    private val coins = Coins()
    private val assets = AssetsLoader.getInstance()

    init {
        stage = Stage(ScreenViewport())
        skin = Skin()
        skin.add("default", game.levels)
        skin.add("ButtonOn", assets.buttonon )
        skin.add("ButtonOff", assets.buttonoff )
        background = Background()
        background.setPosition(0f, 0f)
        stage.addActor(background)

        val textButtonStyle = TextButton.TextButtonStyle()
        textButtonStyle.up = skin.newDrawable("ButtonOn")
        textButtonStyle.down = skin.newDrawable("ButtonOff")
        textButtonStyle.checked = skin.newDrawable("ButtonOn")
        textButtonStyle.over = skin.newDrawable("ButtonOff")
        textButtonStyle.font = skin.getFont("default")
        skin.add("default", textButtonStyle)

        val textButtonStyle2 = TextButton.TextButtonStyle()
        textButtonStyle.up = skin.newDrawable("ButtonOn")
        textButtonStyle.down = skin.newDrawable("ButtonOff")
        textButtonStyle.checked = skin.newDrawable("ButtonOn")
        textButtonStyle.over = skin.newDrawable("ButtonOff")
        textButtonStyle.font = skin.getFont("default")
        skin.add("withChecked", textButtonStyle2)

        table = Table()
        table.setFillParent(true)

        var curLev = pref.getInteger("HammerLevel")
        hammer = TextButton("Hammer, lvl: ${pref.getInteger("HammerLevel")}", skin)
        hammer.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                weaponType = "Hammer"

            }
        })
        var currentCost = curLev * 2

        hammerLevelUp = TextButton("LevelUp: $currentCost", skin)
        hammerLevelUp.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                curLev = pref.getInteger("HammerLevel")
                currentCost = curLev * 2
                if (coins.spendCoins(currentCost.toInt())) {
                    pref.putInteger("HammerLevel", curLev + 1)
                    curLev++
                    currentCost = curLev * 2
                    hammerLevelUp.setText("LevelUp: $currentCost")
                    hammer.setText("Hammer, lvl: $curLev")
                    pref.flush()
                }
            }
        })
        curLev = pref.getInteger("ShovelLevel")
        shovel = TextButton("Shovel, lvl: $curLev", skin)
        shovel.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                weaponType = "Shovel"

            }
        })
        currentCost = curLev * 3
        shovelLevelUp = TextButton("LevelUp: $currentCost", skin)
        shovelLevelUp.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true;
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                curLev = pref.getInteger("ShovelLevel")
                currentCost = curLev * 3
                if (coins.spendCoins(currentCost.toInt())) {
                    pref.putInteger("ShovelLevel", curLev + 1)
                    curLev++
                    currentCost = curLev * 3
                    shovelLevelUp.setText("LevelUp: $currentCost")
                    shovel.setText("Shovel, lvl: $curLev")
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
                game.screen = LevelScreen(game, weaponType)
            }
        })

        table2.row().height(100f)
        table2.add(play).width(100f)
        table2.bottom().right()
        stage.addActor(table2)

        val labelStyle = Label.LabelStyle()
        labelStyle.font = game.font
        labelStyle.fontColor = Color.BLACK
        moneyLabel = Label("MONEY: ${coins.amount}", labelStyle)
        moneyTable = Table()
        moneyTable.add(moneyLabel)
        moneyTable.right().top()
        moneyTable.setFillParent(true)
        stage.addActor(moneyLabel)

        Gdx.input.setInputProcessor(stage)
        Gdx.input.setCatchBackKey(true)
    }


    public override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        moneyLabel.setText("MONEY: ${coins.amount}")
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