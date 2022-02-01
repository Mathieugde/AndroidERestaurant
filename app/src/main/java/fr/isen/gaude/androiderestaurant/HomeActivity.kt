package fr.isen.gaude.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.gaude.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val tvStarter = binding.Appetizers
        val tvMainCourse = binding.MainDish
        val tvDesserts = binding.Dessert


        // set on-click listener
        tvStarter.setOnClickListener {
            val str : String = tvStarter.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Appetizers.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)

        }
        tvMainCourse.setOnClickListener {
            val str: String = tvMainCourse.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Main Dishes.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)

        }
        tvDesserts.setOnClickListener {
            val str :String = tvDesserts.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Dessert.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)


        }



    }


    private fun changeActivityWithCategory(str : String) {


        val monIntent : Intent =  Intent(this,DishActivity::class.java)
        monIntent.putExtra("selectedCategory", str)
        startActivity(monIntent)


    }







}


