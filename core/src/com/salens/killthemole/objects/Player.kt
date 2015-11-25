package com.salens.killthemole.objects

import com.badlogic.gdx.Gdx
import com.salens.killthemole.objects.armour.Armour
import com.salens.killthemole.objects.armour.Barrel
import com.salens.killthemole.objects.weapons.Hammer
import com.salens.killthemole.objects.weapons.Shovel
import com.salens.killthemole.objects.weapons.Weapon

/**
 * Created by Antropov Igor on 19.11.2015.
 */


public class Player() {

    private var armour: Armour
    private var weapon: Weapon
    public var health: Int
    public var attack: Int

    init{
        armour = Barrel(this)
        weapon = Shovel(this)
        health = 100 + armour.moreHealth
        attack = weapon.attack() + armour.moreAttack
    }


    public fun getHurted(amount: Int) {
        health -= amount
        Gdx.app.log("CorrentHealth", "$health")
    }

    public fun attack(): Int {
        weapon.extraAttack()
        return attack
    }
}