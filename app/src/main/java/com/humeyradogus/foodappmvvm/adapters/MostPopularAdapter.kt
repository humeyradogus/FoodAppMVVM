package com.humeyradogus.foodappmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humeyradogus.foodappmvvm.databinding.PopularItemsBinding
import com.humeyradogus.foodappmvvm.pojo.MealsByCategory


class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>(){
    class PopularMealViewHolder(val binding: PopularItemsBinding) : RecyclerView.ViewHolder(binding.root)

    private var mealsList: ArrayList<MealsByCategory> = ArrayList()
    lateinit var onItemClick: ((MealsByCategory) -> Unit)

    fun setMealList(mealsList: ArrayList<MealsByCategory>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}