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
                    9 -> view.score1.append("${Scores.name} : ${Scores.score}")
                    8 -> view.score2.append("${Scores.name} : ${Scores.score}")
                    7 -> view.score3.append("${Scores.name} : ${Scores.score}")
                    6 -> view.score4.append("${Scores.name} : ${Scores.score}")
                    5 -> view.score5.append("${Scores.name} : ${Scores.score}")
                    4 -> view.score6.append("${Scores.name} : ${Scores.score}")
                    3 -> view.score7.append("${Scores.name} : ${Scores.score}")
                    2 -> view.score8.append("${Scores.name} : ${Scores.score}")
                    1 -> view.score9.append("${Scores.name} : ${Scores.score}")
                    0 -> view.score10.append("${Scores.name} : ${Scores.score}")
                }
            }
        }
        return view
    }
}