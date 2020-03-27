package edu.charles_wyatt.simonsez

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity()
{
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
            { model.startGame() }

            override fun finishButtonPressed()
            { gameFragment?.getButtons() }

            override fun redButtonPressed()
            { model.setPlayerSequence(0) }

            override fun greenButtonPressed()
            { model.setPlayerSequence(1) }

            override fun blueButtonPressed()
            { model.setPlayerSequence(3) }

            override fun yellowButtonPressed()
            { model.setPlayerSequence(2) }

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
            { finish() }
        }

        model.listener = object: GameModel.Listener
        {
            override fun sequenceTriggered()
            {
                var count = 0
                val sequence = model.getSequence()

                if (sequence != null)
                {
                    for (index in sequence)
                    {
                        gameFragment?.gameTime(index, count)
                        count++
                    }
                }

                sequence?.add((0..3).shuffled().first())
                if (sequence != null) { model.setSequence(sequence) }
            }

            override fun goToNextRound()
            {
                model.startGame()
            }

            override fun theGameIsOver()
            { gameFragment?.getButtons() }

        }
    }
}
