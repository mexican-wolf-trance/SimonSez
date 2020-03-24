package edu.charles_wyatt.simonsez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setMargins
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var model: GameModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(GameModel::class.java)
        val view = findViewById<RelativeLayout>(R.id.activity_main)

        model.setDifficulty(intent.getStringExtra("diff"))
        Toast.makeText(applicationContext, "The difficulty is ${model.getDifficulty()}", Toast.LENGTH_SHORT).show()

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
            }
        }
        val finishButton = findViewById<TextView>(R.id.finish)
        finishButton.setOnClickListener()
        {
            onFinish()
        }

    }
}
