package com.salens.killthemole.objects

import com.badlogic.gdx.Gdx
import com.salens.killthemole.objects.weapons.Hammer
import com.salens.killthemole.objects.weapons.Weapon

/**
 * Created by Antropov Igor on 19.11.2015.
 */


public class Player() {

    private var health = 100
    private var weapon: Weapon = Hammer()


    public fun getHurted(amount: Int) {
        health -= amount
        Gdx.app.log("CorrentHealth", "$health")
    }

    public fun getHealth(): Int = health
    public fun attack(): Int {
        var a= weapon.attack()
        Gdx.app.log("Attack", "$a")
        return weapon.attack()
    }
}