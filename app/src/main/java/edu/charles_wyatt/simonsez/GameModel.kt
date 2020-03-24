package edu.charles_wyatt.simonsez

import android.nfc.Tag
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameModel: ViewModel()
{
    private var random = Random
    private val sequence: List<Int>? = (1..4).map { random.nextInt(until = 4) }
    private var gameSequence: MutableList<Int>? = sequence?.toMutableList()
    private var diff: String = ""
    private var score: Int = 0
    private var handler: Handler? = null

    var isRunning = false
    private set

//    fun startGame()
//    {
//        if (handler == null)
//        {
//            handler = Handler()
//            handler?.postDelayed(runnable, 1200)
//            isRunning = true
//        }
//    }
//    private var runnable: Runnable = Runnable
//    {
//
//    }

    fun setScore(x: Int)
    {
        this.score = x
    }
    fun setDifficulty(x: String?)
    {
        if (x != null) { this.diff = x }
    }
    fun getDifficulty(): String?
    {
        return this.diff
    }
    fun setSequence(x: MutableList<Int>)
    {
        this.gameSequence = x
    }
    fun getSequence(): MutableList<Int>?
    {
        return gameSequence as MutableList<Int>
    }

}
