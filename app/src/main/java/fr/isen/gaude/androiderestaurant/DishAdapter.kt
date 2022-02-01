package fr.isen.gaude.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.gaude.androiderestaurant.model.DishModel


class DishAdapter(private val mList: List<DishModel>, private val cellClickListener : CellClickListener) : RecyclerView.Adapter<DishAdapter.ViewHolder>(){


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_cell, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = mList[position]

        if(dish.pictures[0]!="") {
            Picasso.get()
                .load(dish.pictures[0])
                .error(R.drawable.pizza)
                .into(holder.itemImage)



        }
        else{
            holder.itemImage.setImageResource(R.drawable.pizza)
        }

        holder.itemText.text = dish.name_fr
        holder.itemprice.text = dish.prices[0].price+"€"


        val data = mList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.dishPicture)
        val itemText: TextView = itemView.findViewById(R.id.dishName)
        val itemprice: TextView=itemView.findViewById(R.id.dishPrice)

    }



}

