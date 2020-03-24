package edu.charles_wyatt.simonsez

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameModel: ViewModel()
{
    private var random = Random
    private var gameSequence = (1..4).map { random.nextInt() }
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
    private fun getRandomSequence(): List<Int>
    {
        return gameSequence
    }

}
