package com.salens.killthemole.objects.weapons

import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Player

/**
 * Created by Antropov Igor on 22.11.2015.
 */


public class Hammer(player: Player) : Weapon(){
    override var damage = AssetsLoader.getInstance().getPrefs().getInteger("HammerDamage")
    override var level = AssetsLoader.getInstance().getPrefs().getInteger("HammerLevel")

    override fun attack() = damage
    override fun extraAttack(){}
}