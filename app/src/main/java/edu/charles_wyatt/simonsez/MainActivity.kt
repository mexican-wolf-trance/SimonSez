package edu.charles_wyatt.simonsez

import android.app.PendingIntent.getActivity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.game_screen_fragment.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: GameModel
    private var scoreFragment: ScoresScreen? = null
    private var gameFragment: GameScreenFragment? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(GameModel::class.java)
        model.setDifficulty(intent.getStringExtra("diff"))

        gameFragment = supportFragmentManager.findFragmentById(R.id.activity_main) as? GameScreenFragment
        if (gameFragment == null)
        {
            gameFragment = GameScreenFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main, gameFragment!!)
                .commit()
        }

        gameFragment?.listener = object: GameScreenFragment.StateListener
        {
            override fun startButtonPressed()
            {
                Log.e("Tag", "Start Button Pressed")
                model.startGame()
            }

            override fun finishButtonPressed()
            {
                gameFragment?.getButtons()
            }

            override fun redButtonPressed()
            {
                Log.e("Tag", "Red Button Pressed")
            }

            override fun greenButtonPressed()
            {
                Log.e("Tag", "Green Button Pressed")
            }

            override fun blueButtonPressed()
            {
                Log.e("Tag", "Blue Button Pressed")
            }

            override fun yellowButtonPressed()
            {
                Log.e("Tag", "Yellow Button Pressed")
            }

            override fun scoresButtonPressed()
            {
                scoreFragment = supportFragmentManager.findFragmentById(R.id.activity_main) as? ScoresScreen
                if (scoreFragment == null)
                {
                    scoreFragment = ScoresScreen()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main, scoreFragment!!)
                        .commit()
                }
            }

            override fun backToStartPressed()
            {
                finish()
            }
        }

        model.listener = object: GameModel.Listener
        {
            override fun sequenceTriggered()
            {
                Log.e("TAG", "Sequence triggerrrrrrrred")
                gameFragment?.gameTime()
            }
        }
    }
}
