package com.salens.killthemole.helpers

/**
 * Created by Antropov Igor on 18.11.2015.
 */
import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.XmlReader
import com.badlogic.gdx.utils.XmlReader.Element
import java.io.IOException
import java.util.*


public class XMLparse {

    private var starsPos: Array<Pair<String, String>> = Array<Pair<String, String>>()

    private var delay: Long = 1
    private var moleHealth: Int = 1
    private var moleAttack: Int = 1

    public fun XMLparseLevels(): Array<String> {
        var levels: Array<String> = Array<String>()
        var int_levels: Array<Int> = Array<Int>()

        val dirHandle: FileHandle

        if (Gdx.app.type == ApplicationType.Android) {
            dirHandle = Gdx.files.internal("data/xml/levels")
        } else {
            dirHandle = Gdx.files.internal(System.getProperty("user.dir") + "/data/assets/xml/levels")
        }

        for (entry in dirHandle.list()) {
            levels.add(entry.name().split(".xml")[0])
        }


        for (i in 0..levels.size - 1) {
            int_levels.add(Integer.parseInt(levels.get(i)))
        }
        int_levels.sort()
        levels.clear()

        for (i in 0..int_levels.size - 1) {
            levels.add(int_levels.get(i).toString())
        }

        return levels
    }

    fun getPos(strLevel: String): Array<Pair<String, String>> {
        try {
            val root = XmlReader().parse(Gdx.files.internal("data/xml/1.xml"))
            this.moleAttack = root.getChildrenByName("attack")[0].getAttribute("attack").toInt()
            this.moleHealth = root.getChildrenByName("health")[0].getAttribute("health").toInt()
            this.delay = root.getChildrenByName("delay")[0].getAttribute("delay").toLong()
            val xml_pos = root.getChildByName("positions").getChildrenByName("position")
            for (el in xml_pos) {
                val xy = Pair<String, String>(el.getAttribute("x"), el.getAttribute("y"))
                this.starsPos.add(xy)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return this.starsPos
    }

    public fun getDelay(): Long {
        return this.delay
    }

    public fun getHealth(): Int = moleHealth
    public fun getAttack(): Int = moleAttack
}