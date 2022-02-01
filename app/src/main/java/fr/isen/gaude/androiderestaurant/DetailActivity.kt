package fr.isen.gaude.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.gaude.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.gaude.androiderestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)
    }
        private fun initDetail(dish: DishModel) {
            var nbInBucket = 1
            binding.detailTitle.text = dish.name_fr

            binding.dishPhotoPager.adapter = DishPictureAdapter(this, dish.pictures)

            binding.dishIngredient.text = dish.ingredients.joinToString(", ") { it.name_fr}

            
        }

}