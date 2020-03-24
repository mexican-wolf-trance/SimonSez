package edu.charles_wyatt.simonsez

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.game_screen_fragment.*
import kotlinx.android.synthetic.main.game_screen_fragment.view.*


class GameScreenFragment: Fragment()
{
    private lateinit var model: GameModel

    interface StateListener
    {
        fun startButtonPressed()
        fun finishButtonPressed()
        fun redButtonPressed()
        fun greenButtonPressed()
        fun blueButtonPressed()
        fun yellowButtonPressed()

    }
    var listener: StateListener? = null


    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        activity?.apply {
            model = ViewModelProvider(this).get(GameModel::class.java) }
        Log.e("TAG", "Am I here?")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val thisView = inflater.inflate(R.layout.game_screen_fragment, container, false)
        thisView.game_start.setOnClickListener()
        {
            listener?.startButtonPressed()
        }
        thisView.finish.setOnClickListener()
        {
            listener?.finishButtonPressed()
        }
        thisView.red.setOnClickListener()
        {
            listener?.redButtonPressed()
        }
        thisView.green.setOnClickListener()
        {
            listener?.greenButtonPressed()
        }
        thisView.blue.setOnClickListener()
        {
            listener?.blueButtonPressed()
        }
        thisView.yellow.setOnClickListener()
        {
            listener?.yellowButtonPressed()
        }

        return thisView
    }

    fun runUIUpdate()
    {
        var sequence = model.getSequence()
        for (index in 0 until 4)
        {
            val view = when(index)
            {
                1 ->
            }
        }
    }


//    val view = findViewById<RelativeLayout>(R.id.game_screen_layout)
//
//    val gameStart = findViewById<Button>(R.id.game_start)
//    val finishButton = findViewById<TextView>(R.id.finish)
//    val redButton = findViewById<TextView>(R.id.red)
//    val greenButton = findViewById<TextView>(R.id.green)
//    val blueButton = findViewById<TextView>(R.id.blue)
//    val yellowButton = findViewById<TextView>(R.id.yellow)

//    fun backToStart()
//    {
//        finish
//    }
//    gameStart.append(": ${model.getDifficulty()} MODE")
//
//    fun onFinish()
//    {
//        gameStart.isEnabled = false
//        finishButton.isEnabled = false
//        redButton.isEnabled = false
//        greenButton.isEnabled = false
//        blueButton.isEnabled = false
//        yellowButton.isEnabled = false
//
//        val seeScoresButtonParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        seeScoresButtonParams.setMargins(200, 600, 200, 0)
//        val playAgainButtonParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        playAgainButtonParams.setMargins(200, 400, 200, 0)
//
//        val seeScoresButton = Button(this)
//        val playAgainButton = Button(this)
//
//        seeScoresButton.setText(R.string.congrats)
//        seeScoresButton.textSize = 30.0F
//        seeScoresButton.layoutParams = seeScoresButtonParams
//
//        playAgainButton.setText(R.string.play_again)
//        playAgainButton.textSize = 30.0F
//        playAgainButton.layoutParams = playAgainButtonParams
//
//        view.addView(seeScoresButton)
//        view.addView(playAgainButton)
//
//        seeScoresButton.setOnClickListener()
//        {
//        }
//        playAgainButton.setOnClickListener()
//        {
//            backToStart()
//        }
//
//        finishButton.setOnClickListener()
//        {
//            onFinish()
//        }
//        gameStart.setOnClickListener()
//        {
//            var sequence: MutableList<Int>? = model.getSequence()
//            sequence?.add((0..3).shuffled().first())
//            Log.e("TAG", "Sequence is $sequence")
//            if (sequence != null)
//            {
//                model.setSequence(sequence)
//            }
//        }
//
//    }
}