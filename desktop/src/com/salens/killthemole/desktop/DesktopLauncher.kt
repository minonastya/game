package com.salens.killthemole.desktop

/**
 * Created by Antropov Igor on 15.11.2015.
 */

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.salens.killthemole.KillTheMole

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.title = "NASTJA"
    LwjglApplication(KillTheMole(), config)
}

