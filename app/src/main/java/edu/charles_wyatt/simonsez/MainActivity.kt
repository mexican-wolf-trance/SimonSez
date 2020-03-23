package edu.charles_wyatt.simonsez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var model: GameModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(GameModel::class.java)

        val diff = intent.getStringExtra("diff")
        Toast.makeText(applicationContext, "The difficulty is $diff", Toast.LENGTH_SHORT).show()
    }
}
