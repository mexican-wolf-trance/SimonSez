package edu.charles_wyatt.simonsez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.start_screen.*


class StartScreen : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)

        val gameIntent = Intent(this@StartScreen, MainActivity::class.java)

        val startScreen = findViewById<LinearLayout>(R.id.start_screen)
        val easyParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val medParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val backParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM.toFloat())
        easyParams.setMargins(100,200,100,0)
        medParams.setMargins(100, 0, 100, 0)
        backParams.setMargins(100, 550, 100, 0)

        val hardButton = Button(this)
        hardButton.setText(R.string.hard)
        hardButton.layoutParams = medParams
        hardButton.textSize = 30.0F


        val medButton = Button(this)
        medButton.setText(R.string.medium)
        medButton.layoutParams = medParams
        medButton.textSize = 30.0F


        val easyButton = Button(this)
        easyButton.setText(R.string.easy)
        easyButton.layoutParams = easyParams
        easyButton.textSize = 30.0F

        val backButton = Button(this)
        backButton.setText(R.string.back)
        backButton.layoutParams = backParams
        backButton.textSize = 30.0F

        val playGame = findViewById<View>(R.id.play_button) as Button
        val topText = findViewById<TextView>(R.id.start_text)

        fun chooseDifficulty()
        {
            playGame.visibility = View.GONE
            topText.setText(R.string.set_diff)

            startScreen.addView(easyButton)
            startScreen.addView(medButton)
            startScreen.addView(hardButton)
            startScreen.addView(backButton)
        }

        playGame.setOnClickListener()
        {
            chooseDifficulty()
        }


        easyButton.setOnClickListener()
        {
            gameIntent.putExtra("diff", "EASY")
            startActivity(gameIntent)
        }
        medButton.setOnClickListener()
        {
            gameIntent.putExtra("diff", "NORMAL")
            startActivity(gameIntent)
        }
        hardButton.setOnClickListener()
        {
            gameIntent.putExtra("diff", "HARD")
            startActivity(gameIntent)
        }
        backButton.setOnClickListener()
        {
            startScreen.removeView(easyButton)
            startScreen.removeView(medButton)
            startScreen.removeView(hardButton)
            startScreen.removeView(backButton)

            playGame.visibility = View.VISIBLE

            topText.setText(R.string.welcome)
        }
    }
}