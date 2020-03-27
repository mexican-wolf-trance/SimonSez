package edu.charles_wyatt.simonsez

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.scores_screen_fragment.view.*

class ScoresScreen : Fragment()
{
    private lateinit var model: GameModel

    interface StateListener
    {
            fun backToStart()
    }
    var listener: StateListener? = null


    override fun onAttach(context: Context)
    { super.onAttach(context)
        activity?.apply {
            model = ViewModelProvider(this).get(GameModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    { val view =  inflater.inflate(R.layout.scores_screen_fragment, container, false)
        view.play_again.setOnClickListener()
        {
            activity?.finish()
        }
        return view
    }
}