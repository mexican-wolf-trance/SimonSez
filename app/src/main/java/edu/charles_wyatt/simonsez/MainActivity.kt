package edu.charles_wyatt.simonsez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setMargins
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.scores_screen_fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.game_screen_fragment.*

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
        val gameStart = findViewById<Button>(R.id.game_start)
        gameStart.append(": ${model.getDifficulty()} MODE")
//        val view = findViewById<RelativeLayout>(R.id.activity_main)
//        val finishButton = findViewById<TextView>(R.id.finish)
//        val redButton = findViewById<TextView>(R.id.red)
//        val greenButton = findViewById<TextView>(R.id.green)
//        val blueButton = findViewById<TextView>(R.id.blue)
//        val yellowButton = findViewById<TextView>(R.id.yellow)
//
//        fun backToStart()
//        {
//            finish()
//        }
//        fun onFinish()
//        {
//            gameStart.isEnabled = false
//            finishButton.isEnabled = false
//            redButton.isEnabled = false
//            greenButton.isEnabled = false
//            blueButton.isEnabled = false
//            yellowButton.isEnabled = false
//
//            val seeScoresButtonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            seeScoresButtonParams.setMargins(200, 600, 200, 0)
//            val playAgainButtonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            playAgainButtonParams.setMargins(200, 400, 200, 0)
//
//            val seeScoresButton = Button(this)
//            val playAgainButton = Button(this)
//
//            seeScoresButton.setText(R.string.congrats)
//            seeScoresButton.textSize = 30.0F
//            seeScoresButton.layoutParams = seeScoresButtonParams
//
//            playAgainButton.setText(R.string.play_again)
//            playAgainButton.textSize = 30.0F
//            playAgainButton.layoutParams = playAgainButtonParams
//
//            view.addView(seeScoresButton)
//            view.addView(playAgainButton)
//
//            seeScoresButton.setOnClickListener()

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
                scoreFragment = supportFragmentManager.findFragmentById(R.id.activity_main) as? ScoresScreen
                if (scoreFragment == null)
                {
                    scoreFragment = ScoresScreen()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main, scoreFragment!!)
                        .commit()
                }
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
        }
        model.listener = object: GameModel.Listener
        {
            override fun sequenceTriggered()
            {
                Log.e("TAG", "Sequence triggerrrrrrrred")
                gameFragment?.runUIUpdate()
            }
        }

//            playAgainButton.setOnClickListener()
//            {
//                backToStart()
//            }
//        }
//        finishButton.setOnClickListener()
//        {
//            onFinish()
//        }
//        gameStart.setOnClickListener()
//        {
//            var sequence: MutableList<Int>? = model.getSequence()
//            sequence?.add((0..3).shuffled().first())
//            Log.e("TAG", "Sequence is $sequence")
//            if (sequence != null) {
//                model.setSequence(sequence)
//            }
//        }

    }
}
