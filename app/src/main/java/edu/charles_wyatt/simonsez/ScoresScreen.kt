package edu.charles_wyatt.simonsez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ScoresScreen : Fragment()
{
    interface StateListener
    {
            fun startButtonPressed()
    }
    var listener: StateListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.scores_sceen_fragment, container, false)
        return view
    }


}