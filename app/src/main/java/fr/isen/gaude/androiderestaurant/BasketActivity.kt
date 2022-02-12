package fr.isen.gaude.androiderestaurant

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.gaude.androiderestaurant.RegisterFragment.Companion.USER_ID
import fr.isen.gaude.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.gaude.androiderestaurant.model.BasketData
import fr.isen.gaude.androiderestaurant.model.DishBasket
import java.io.File


class BasketActivity : ToolActivity() {
    private lateinit var binding: ActivityBasketBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences(DetailActivity.APP_PREFS, Context.MODE_PRIVATE)
        binding.basketTitle.text = "Mon Panier"
        verifIfConnect()

        val filename = "/panier.json"
        if (File(cacheDir.absolutePath + filename).exists()) {
            val recup = File(cacheDir.absolutePath + filename).readText()
            val resultat = Gson().fromJson(recup, DishBasket::class.java)
            Log.d("panier", recup)
            val data = ArrayList<BasketData>()
            for (j in resultat.dishName.indices) {
                data.add(BasketData(resultat.dishName[j].DishName, resultat.dishName[j].quantity))
            }

            displayDishes(DishBasket(data, resultat.quantity))
        }
        var buttonConnection = binding.connectionBtn
        buttonConnection.setOnClickListener {
            if (binding.connectionBtn.text == "Commander") {
                startActivity(Intent(this, OrderActivity::class.java))
            } else {
                startActivity(Intent(this, ConnectionActivity::class.java))
            }
        }
    }

    private fun displayDishes(dishResult: DishBasket) {
        binding.basketItem.layoutManager = LinearLayoutManager(this)
        binding.basketItem.adapter = BasketAdapter(dishResult.dishName) {
            dishResult.dishName.remove(it)
            updateBasket(dishResult)
            invalidateOptionsMenu()
        }


    }

    private fun updateBasket(basket: DishBasket) {
        val filename = "/panier.json"
        dishCountInPref(basket)
        File(cacheDir.absolutePath + filename).writeText(
            GsonBuilder().create().toJson(basket)
        )
    }

    private fun dishCountInPref(basket: DishBasket) {
        val count = basket.dishName.sumOf { it.quantity }
        basket.quantity = count
        val editor = getSharedPreferences(DetailActivity.APP_PREFS, Context.MODE_PRIVATE).edit()
        editor.putInt(DetailActivity.basketCount, count)
        editor.apply()

    }

    private fun verifIfConnect() {
        val userIdSave =
            getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE).contains(USER_ID)
        if (userIdSave) {
            binding.connectionBtn.text = "Commander"
            Log.e("","")
        }
    }
}