package edu.charles_wyatt.simonsez

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        fun scoresButtonPressed()
        fun backToStartPressed()

    }
    var listener: StateListener? = null

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        activity?.apply {
            model = ViewModelProvider(this).get(GameModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    { val thisView = inflater.inflate(R.layout.game_screen_fragment, container, false)
        thisView.game_start.append(": ${model.getDifficulty()} MODE")
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

    fun getButtons()
    {
        game_start.isEnabled = false
        finish.isEnabled = false
        red.isEnabled = false
        green.isEnabled = false
        yellow.isEnabled = false
        blue.isEnabled = false

        go_to_the_scores.visibility = View.VISIBLE
        back_to_start.visibility = View.VISIBLE
        go_to_the_scores.setOnClickListener()
        {
            listener?.scoresButtonPressed()
        }
        back_to_start.setOnClickListener()
        {
            listener?.backToStartPressed()
        }
    }

    fun gameTime(index: Int, count: Int)
    {
        activity?.let {activity ->
            val view = when (index)
            {
                0 -> red
                1 -> green
                2 -> yellow
                3 -> blue
                else -> blue
            }
            val originalColor = view.background as? ColorDrawable
            val blackColor = ContextCompat.getColor(activity, R.color.black)
            val animator = ValueAnimator.ofObject(
                ArgbEvaluator(),
                originalColor?.color,
                blackColor,
                originalColor?.color
            )
            animator.addUpdateListener { valueAnimator ->
                (valueAnimator.animatedValue as? Int)?.let { animatedValue ->
                    view.setBackgroundColor((animatedValue))
                }
            }
            when (model.getDifficulty())
            {
                "EASY" ->
                {
                    animator?.startDelay = (count * 1000).toLong()
                    animator?.duration = 1000
                    animator?.start()
                }
                "NORMAL" ->
                {
                    animator?.startDelay = (count * 900).toLong()
                    animator?.duration = 900
                    animator?.start()
                }
                "HARD" ->
                {
                    animator?.startDelay = (count * 800).toLong()
                    animator?.duration = 800
                    animator?.start()
                }
                else ->
                {
                    animator?.startDelay = (count * 1000).toLong()
                    animator?.duration = 1000
                    animator?.start()
                }
            }
        }
    }
}