package edu.charles_wyatt.simonsez

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameModel: ViewModel()
{
    private var random = Random
    private var gameSequence = (1..10).map { random.nextInt() }
    //difficult will be represented by either 1, 2, or 3, with 3 being the hardest
    private var diff: Int = 0

    private fun setDifficulty(x: Int)
    {
        this.diff = x
    }

    private fun getRandomSequence(): List<Int>
    {
        return gameSequence
    }

}
