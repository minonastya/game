package com.salens.killthemole.objects.weapons

import com.salens.killthemole.KillTheMole
import com.salens.killthemole.helpers.AssetsLoader
import com.salens.killthemole.objects.Player

/**
 * Created by Antropov Igor on 23.11.2015.
 */


public class Shovel(val player: Player): Weapon(){
    override var damage = AssetsLoader.getInstance().getPrefs().getInteger("ShovelDamage")
    override var level = AssetsLoader.getInstance().getPrefs().getInteger("ShovelLevel")

    override fun attack() = damage * level
    override fun extraAttack(){
        if(player.health < 100) player.health += damage / 5
    }
}
