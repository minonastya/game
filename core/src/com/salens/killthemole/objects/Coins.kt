package com.salens.killthemole.objects

import com.badlogic.gdx.Gdx

/**
 * Created by Antropov Igor on 26.11.2015.
 */
public class Coins(){

    private var amount: Int

    init {
        amount = Gdx.app.getPreferences("KillTheMole").getInteger("Coins")
        Gdx.app.log("AAAAA", "$amount")
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
        Gdx.app.log("Coins", "$amount")
    }
}