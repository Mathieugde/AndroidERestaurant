package fr.isen.gaude.androiderestaurant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import fr.isen.gaude.androiderestaurant.databinding.ActivityOrderBinding
import fr.isen.gaude.androiderestaurant.model.DishBasket
import org.json.JSONObject
import java.io.File

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val filename = "/panier.json"
        if (File(cacheDir.absolutePath + filename).exists()) {
            if (File(cacheDir.absolutePath + filename).readText()
                    .isNotEmpty()
            ) {
                val jsonData = JSONObject()
                jsonData.put("msg", File(cacheDir.absolutePath + filename).readText())
                jsonData.put("id_shop", "1")
                jsonData.put(
                    "id_user",
                    getSharedPreferences(
                        DetailActivity.APP_PREFS,
                        MODE_PRIVATE
                    ).getString(LoginFragment.USER_ID, "")
                )

                val queue = Volley.newRequestQueue(this)
                val url = "http://test.api.catering.bluecodegames.com/user/order"
                val jsonObject = jsonData
                val recup = File(cacheDir.absolutePath + filename).readText()
                val resultat = Gson().fromJson(recup, DishBasket::class.java)
                val request = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    { response ->
                        if (resultat.quantity == 0) displayPage(true)
                        else displayPage(false)


                    }, {
                        displayPage(true)
                    })
                request.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,

                    0,
                    1f
                )
                queue.add(request)
            } else displayPage(true)
        } else displayPage(true)
    }

    private fun displayPage(error: Boolean) {
        if (error) {
            binding.orderStatusOK.isVisible = false
            binding.orderStatusError.isVisible = true
        } else {
            showGif(binding.root)
            binding.warning.isVisible = false
            binding.orderStatusOK.isVisible = true
            binding.orderStatusError.isVisible = false
        }
    }

    private fun showGif(view: View) {
        val imageView: ImageView = findViewById(R.id.delivery_gif)
        Glide.with(this).load(R.drawable.dinogif).into(imageView)
    }

}