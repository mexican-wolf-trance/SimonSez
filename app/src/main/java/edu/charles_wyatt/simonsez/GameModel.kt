package edu.charles_wyatt.simonsez

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameModel: ViewModel()
{
    private var random = Random
    private var gameSequence: List<Int>? = null
    private var diff: String = ""

    fun setDifficulty(x: String?)
    {
        if (x != null)
        {
            this.diff = x
        }
    }
    fun getDifficulty(): String?
    {
        return this.diff
    }
    fun getRandomSequence(): List<Int>
    {
        gameSequence = (1..4).map { random.nextInt(until = 4) }
        return gameSequence as List<Int>
    }

}
