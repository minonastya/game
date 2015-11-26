package com.salens.killthemole.objects.weapons

import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Player

/**
 * Created by Antropov Igor on 22.11.2015.
 */


abstract class Weapon() {
    abstract var damage: Int
    abstract var level: Int

    abstract fun attack(): Int
    abstract fun extraAttack()
}

