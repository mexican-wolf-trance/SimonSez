package edu.charles_wyatt.simonsez

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.game_screen_fragment.view.*
import kotlin.random.Random

class GameModel: ViewModel()
{

    var listener: Listener? = null
    interface Listener
    {
        fun sequenceTriggered()
        //fun setPlayerSequence()
    }


    private var random = Random
    private var sequence: List<Int>? = null
    private var gameSequence: MutableList<Int>? = null
    private var playerSequence: MutableList<Int>? = arrayListOf()
    private var diff: String = ""
    private var score: Int = 0
    private var handler: Handler? = null

    var isRunning = false
    private set

    fun startGame()
    {
        if (handler == null)
        {
            handler = Handler()
            handler?.postDelayed(runnable, 1200)
            isRunning = true
        }
    }
    private var runnable: Runnable = Runnable {
        listener?.sequenceTriggered()
        this.stopGame()
    }

    private fun stopGame()
    {
        handler?.removeCallbacks(runnable)
        isRunning = false
        handler = null
    }

    private fun triggerSequence()
    {
        handler?.postDelayed(runnable, 1200)
    }

    fun setScore(x: Int)
    {
        this.score = x
    }
    fun getScore(): Int
    {
        return this.score
    }
    fun setDifficulty(x: String?)
    {
        if (x != null) { this.diff = x }
        when (this.diff)
        {
            "EASY" -> sequence = (1..2).map { random.nextInt(until = 4) }
            "NORMAL" -> sequence = (1..3).map { random.nextInt(until = 4) }
            "HARD" -> sequence =(1..5).map { random.nextInt(until = 4) }
        }
        gameSequence = sequence?.toMutableList()
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
    fun setPlayerSequence(x: Int)
    {
        this.playerSequence?.add(x)
        Log.e("TAG", "Player sequence: ${this.playerSequence}")
    }
    fun compareSequence()
    {

    }
}
