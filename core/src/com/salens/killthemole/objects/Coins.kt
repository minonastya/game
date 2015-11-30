package com.salens.killthemole.objects

import com.badlogic.gdx.Gdx

/**
 * Created by Antropov Igor on 26.11.2015.
 */
public class Coins(){

    public var amount: Int
    private val pref = Gdx.app.getPreferences("KillTheMole")

    init {
        amount = pref.getInteger("Coins")
    }

    public fun spendCoins(amount: Int): Boolean{
        if (amount <= this.amount) {
            this.amount -= amount
            pref.putInteger("Coins", this.amount)
            pref.flush()
            return true
        }
        else return false
    }

    public fun addCoins(amount: Int){
        this.amount += amount
        pref.putInteger("Coins", this.amount)
        pref.flush()
    }
}