package edu.charles_wyatt.simonsez

import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import java.io.File
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.IOException
import android.content.Context
import android.view.View
import java.security.AccessController.getContext

class GameModel: ViewModel()
{

    var listener: Listener? = null
    interface Listener
    {
        fun sequenceTriggered()
        fun theGameIsOver()
        fun goToNextRound()
        fun saveScores()
    }


    private var random = Random
    private var sequence: List<Int>? = arrayListOf()
    private var gameSequence: MutableList<Int>? = arrayListOf()
    private var playerSequence: MutableList<Int>? = arrayListOf()
    private var diff: String = ""
    private var score: Int = 0
    var theScores: Scores = Scores("", 0, 0)
    lateinit var scoreList: MutableList<Scores>
    private var handler: Handler? = null
    private var index = 0
    private var playerId = 0

    var isRunning = false
    private set

    fun startGame()
    {
        if (handler == null)
        {
            handler = Handler()
            handler?.postDelayed(runnable, 1200)
            isRunning = true
            this.playerSequence = arrayListOf()
            this.index = 0
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

    private fun setScore(x: Int)
    {
        this.score += x
    }
    fun getScore(): Int
    {
        return this.score
    }
    fun setID()
    {
        this.playerId += 1
    }
    fun getID(): Int
    {
        return this.playerId
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
        Log.e("TAG", "Game sequence : ${this.gameSequence} and game sequence size : ${this.gameSequence?.size} and Index : ${this.index} and x : $x")
        if (this.index <= this.gameSequence?.size!!)
        {
            if (this.gameSequence?.get(this.index) == x)
            {
                this.playerSequence?.add(x)
                this.index++
                if (this.index == (this.gameSequence?.size!!)-1)
                {
                    setScore(1)
                    nextRound()
                }
            }
            else
            {
                saveScoresToJSON()
                endGame()
            }
        }
    }
    fun getPlayerSequence(): MutableList<Int>?
    {
        return playerSequence
    }
    private fun endGame()
    {
        listener?.theGameIsOver()
    }
    private fun nextRound()
    {
        listener?.goToNextRound()
    }
    private fun saveScoresToJSON()
    {
        listener?.saveScores()
    }
}
