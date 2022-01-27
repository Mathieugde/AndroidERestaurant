package fr.isen.gaude.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.gaude.androiderestaurant.databinding.ActivityDishBinding

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dish)

        val category = intent.getStringExtra("category_type")
        findViewById<TextView>(R.id.mainDishTitle).text = category

        //binding.mainDishTitle.text = intent.getStringExtra("category_type")

        binding.dishList.layoutManager = LinearLayoutManager(this)

        val dishes = listOf(
            Dish("Pizza Royale", R.drawable.pizza_royale, "13€"),
            Dish("Pizza 4 fromages", R.drawable.pizza_4_fromages, "12€")
        )

        binding.dishList.adapter = DishAdapter(dishes)

    }
}