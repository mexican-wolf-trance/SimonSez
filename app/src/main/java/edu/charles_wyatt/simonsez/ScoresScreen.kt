package edu.charles_wyatt.simonsez

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.scores_screen_fragment.*
import kotlinx.android.synthetic.main.scores_screen_fragment.view.*
import kotlinx.android.synthetic.main.scores_screen_fragment.view.score1

class ScoresScreen : Fragment() {
    private lateinit var model: GameModel

    interface StateListener {
        //        fun putUpTheScore()
        fun showJSONScores()
    }

    var listener: StateListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.apply {
            model = ViewModelProvider(this).get(GameModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.scores_screen_fragment, container, false)
        view.play_again.setOnClickListener()
        {
            activity?.finish()
        }
        if (model.getGameCheck()) {
            Log.e("TAG", "Show the scores")
            var sortedList: List<Scores> = model.scoreList.sortedWith(compareBy { it.score })

            sortedList.forEachIndexed { item, Scores ->
                Log.i("DATA", "Score is ${Scores.score}")
                when (item) {
                    0 -> view.score1.append(Scores.score.toString())
                    1 -> view.score2.append(Scores.score.toString())
                    2 -> view.score3.append(Scores.score.toString())
                    3 -> view.score4.append(Scores.score.toString())
                    4 -> view.score5.append(Scores.score.toString())
                    5 -> view.score6.append(Scores.score.toString())
                    6 -> view.score7.append(Scores.score.toString())
                    7 -> view.score8.append(Scores.score.toString())
                    8 -> view.score9.append(Scores.score.toString())
                    9 -> view.score10.append(Scores.score.toString())
                }
            }
        }
        return view
    }
}