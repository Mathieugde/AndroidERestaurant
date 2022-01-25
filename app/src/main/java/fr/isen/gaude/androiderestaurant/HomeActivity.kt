package fr.isen.gaude.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import fr.isen.gaude.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Appetizers.text = getString(R.string.home_starters)
        binding.MainDish.text = getString(R.string.home_main_dishes)
        binding.Dessert.text = getString(R.string.home_desserts)

        binding.Appetizers.setOnClickListener{
            changeActivity(getString(R.string.home_starters))
        }

        binding.MainDish.setOnClickListener{
            changeActivity(getString(R.string.home_main_dishes))
        }

        binding.Dessert.setOnClickListener{
            changeActivity(getString(R.string.home_desserts))
        }
    }

    private fun changeActivity( category: String ) {
        val changePage = Intent(this@HomeActivity, DishActivity::class.java)
        changePage.putExtra("category_type",category)
        Log.i("INFO", "End of HomeActivity")
        startActivity(changePage)
    }
}