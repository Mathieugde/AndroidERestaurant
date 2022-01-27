package fr.isen.gaude.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.gaude.androiderestaurant.databinding.CategoryCellBinding


class DishAdapter(val dishes: List<Dish>): RecyclerView.Adapter<DishAdapter.DishViewHolder>(){

    class DishViewHolder(val binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishPicture
        val dishName = binding.dishName
        val dishPrice = binding.dishPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.dishName.text = dishes[position].name
        holder.dishPicture.setImageResource(dishes[position].picture)
        holder.dishPrice.text = dishes[position].price
    }

    override fun getItemCount(): Int = dishes.size
}