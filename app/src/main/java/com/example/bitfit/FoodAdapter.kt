package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val foodlist: List<FoodEntity>): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTV: TextView
        val amountTV: TextView

        init {
            foodTV = itemView.findViewById(R.id.food)
            amountTV = itemView.findViewById(R.id.amount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_food, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodlist[position]
        holder.foodTV.text = item.foodname
        holder.amountTV.text = item.calories
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }


}