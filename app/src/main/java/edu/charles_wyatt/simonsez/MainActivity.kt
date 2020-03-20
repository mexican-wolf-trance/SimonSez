package edu.charles_wyatt.simonsez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diff = intent.getStringExtra("diff")
        Toast.makeText(applicationContext, "The difficulty is $diff", Toast.LENGTH_SHORT).show()
    }
}
