package com.salens.killthemole.objects.armour

import com.salens.killthemole.objects.Player

/**
 * Created by Antropov Igor on 23.11.2015.
 */

public class Barrel(val player: Player): Armour(){
    override val moreAttack: Int = 20
    override val moreHealth: Int = -50


}