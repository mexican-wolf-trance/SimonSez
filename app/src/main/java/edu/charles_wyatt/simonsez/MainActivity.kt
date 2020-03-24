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

class MainActivity : AppCompatActivity() {

    private lateinit var model: GameModel
    private var viewFragment: ScoresScreen? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(GameModel::class.java)
        model.setDifficulty(intent.getStringExtra("diff"))

        val view = findViewById<RelativeLayout>(R.id.activity_main)

        val gameStart = findViewById<Button>(R.id.game_start)
        gameStart.append(": ${model.getDifficulty()} MODE")

        fun onFinish()
        {
            val seeScoresButtonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            seeScoresButtonParams.setMargins(200, 650, 200, 0)

            val seeScoresButton = Button(this)
            seeScoresButton.setText(R.string.congrats)
            seeScoresButton.textSize = 30.0F
            seeScoresButton.layoutParams = seeScoresButtonParams

            view.addView(seeScoresButton)

            seeScoresButton.setOnClickListener()
            {
                Toast.makeText(applicationContext, "So you wanna see scores, eh?", Toast.LENGTH_SHORT).show()
                viewFragment = supportFragmentManager.findFragmentById(R.id.come_see_scores) as? ScoresScreen
                if (viewFragment == null)
                {
                    viewFragment = ScoresScreen()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.come_see_scores, viewFragment!!)
                        .commit()
                }
            }
        }
        val finishButton = findViewById<TextView>(R.id.finish)
        finishButton.setOnClickListener()
        {
            onFinish()
        }
        gameStart.setOnClickListener()
        {
            var sequence: MutableList<Int>? = model.getSequence()
            if (sequence != null) {
                sequence.add((0..3).shuffled().first())
            }
            Log.e("TAG", "Sequence is $sequence")
            if (sequence != null) {
                model.setSequence(sequence)
            }
        }

    }
}
