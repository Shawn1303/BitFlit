package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        findViewById<Button>(R.id.record_button).setOnClickListener {
            val data =Intent()
            val foodString = findViewById<EditText>(R.id.foodinput).text.toString()
            val calorieString = findViewById<EditText>(R.id.calorieinput).text.toString()

            data.putExtra("FOOD_KEY", foodString)
            data.putExtra("CAL_KEY", calorieString)

            setResult(RESULT_OK, data)

            finish()
        }
    }
}