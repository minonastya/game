package com.salens.killthemole.objects

import com.badlogic.gdx.Gdx

/**
 * Created by Antropov Igor on 26.11.2015.
 */
public class Coins(){

    public var amount: Int

    init {
        amount = Gdx.app.getPreferences("KillTheMole").getInteger("Coins")
    }

    public fun spendCoins(amount: Int): Boolean{
        if (amount <= this.amount) {
            this.amount -= amount
            Gdx.app.getPreferences("KillTheMole").putInteger("Coins", this.amount)
            return true
        }
        else return false
    }

    public fun addCoins(amount: Int){
        this.amount += amount
        Gdx.app.getPreferences("KillTheMole").putInteger("Coins", this.amount)
        Gdx.app.getPreferences("KillTheMole").flush()
    }
}