package edu.charles_wyatt.simonsez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ScoresScreen : Fragment()
{
    private lateinit var model: GameModel

    interface StateListener
    {
            fun startButtonPressed()
    }
    var listener: StateListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activity?.apply {
            model = ViewModelProvider(this).get(GameModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.scores_sceen_fragment, container, false) }


}