package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val foods = mutableListOf<FoodEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        foodsRecyclerView = findViewById(R.id.foodRecycler)
        val foodAdapter = FoodAdapter(foods)
        foodsRecyclerView.adapter = foodAdapter

        foodsRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch(IO) {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(
                        entity.foodname,
                        entity.calories,
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        val add = findViewById<Button>(R.id.add_food_button)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val data: Intent? = result.data

                if (data != null) {
                    val foodString = data.getStringExtra("FOOD_KEY")
                    val calorieString = data.getStringExtra("CAL_KEY")
                    if (foodString != null && calorieString != null) {
                        Log.i("shawn", foodString)
                        Log.i("shawn", calorieString)
                        var food = FoodEntity(foodString, calorieString)
                        lifecycleScope.launch(IO) {
                            (application as FoodApplication).db.foodDao().insert(food)
                        }
                        foods.add(food)
                        foodAdapter.notifyDataSetChanged()
                    }

                }


                foodAdapter.notifyDataSetChanged()
            }
        add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            resultLauncher.launch(intent)
        }

//        findViewById<RelativeLayout>(R.id.background).setOnClickListener {
//            lifecycleScope.launch(IO) {
//                (application as FoodApplication).db.foodDao().deleteAll()
//            }
//        }
    }
}