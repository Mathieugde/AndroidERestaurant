package fr.isen.gaude.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.gaude.androiderestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var item: String? = ""


        val itemDish= intent.getSerializableExtra("itemDish") as DishModel



        val detail_title = findViewById<TextView>(R.id.detailTitle)
        detail_title.setText(itemDish.name_fr)
        val detail_image = findViewById<ImageView>(R.id.dishPicture)
        if(itemDish.images[0]!="") {
            Picasso.get()
                .load(itemDish.images[0])
                .error(R.drawable.pizza)
                .into(detail_image)



        }
        else{
            detail_image.setImageResource(R.drawable.pizza)
        }

        val detail_price = findViewById<TextView>(R.id.detailprice)
        detail_price.setText(itemDish.prices[0].price + "€")

        val detail_text = findViewById<TextView>(R.id.detailtext)

        for (i in itemDish.ingredients)
            detail_text.append(i.name_fr + " ")












    }
}