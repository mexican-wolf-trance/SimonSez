package edu.charles_wyatt.simonsez

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


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

        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE )

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
            {
                model.saveScoresToJSON()
                gameFragment?.getButtons()

            }

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
            {
                gameFragment?.getButtons()
            }

            override fun generateTheList()
            {
                model.scoreList = mutableListOf(Scores("Tom", 0, 0))
                for (item in 1 until 10)
                {
                    model.scoreList.add(Scores("Tom", 0, item))
                }
//                val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                val gsonPretty = Gson()
                val jsonScoresPretty = gsonPretty.toJson(model.scoreList)
                Log.e("TAG", "ScoreList: $jsonScoresPretty")

                val prefsEditor: Editor = sharedPref.edit()
                prefsEditor.putString("Scores", jsonScoresPretty);
                prefsEditor.apply();
            }
            override fun saveScores()
            {
//                getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit().clear().apply()
                Log.e("TAG", "What about showJSONScores?")
                val gson = Gson()
                var json = sharedPref.getString("Scores", "")
                Log.e("TAG", "In the Score Fragment, Scores: $json")
                if (json != null)
                {
                    if(json.isEmpty())
                    {
                        model.generateList()
                        json = sharedPref.getString("Scores", "")
                    }
                }

                val scoresType = object : TypeToken<MutableList<Scores>>() {}.type
                val obj: MutableList<Scores> = gson.fromJson(json, scoresType)
                obj.forEachIndexed { idx, scores -> Log.i("data", "> Item $idx:\n$scores") }
                model.scoreList = obj
                Log.e("TAG", "Did it work?, ScoreList: ${model.scoreList.elementAt(0)}")
                var temp = 0
                var tempName = ""
                var score = model.getScore()
                model.scoreList.forEach { Scores ->
                    Log.i("DATA", "Score id: ${Scores.id}, name: ${Scores.name}, score: ${Scores.score}")
//                    if (model.getScore() > Scores.score)
//                    {
//                        temp = Scores.score
//                        Scores.score = model.getScore()
//                    }
//
                }
                for (item in 0 until 9)
                {
                    if (score > model.scoreList.elementAt(item).score)
                    {
                        temp = model.scoreList.elementAt(item).score

                        model.scoreList.elementAt(item).name = "Tom"

                        model.scoreList.elementAt(item).score = score

                        score = temp

                        break
                    }
                }


                val prefsEditor: Editor = sharedPref.edit()
                val gsonPretty = Gson()
                val jsonScoresPretty = gsonPretty.toJson(model.scoreList)
                Log.e("TAG", "JSON: $jsonScoresPretty")
                prefsEditor.putString("Scores", jsonScoresPretty)
                prefsEditor.apply()

                model.setGameCheck()

            }

        }
    }
}
