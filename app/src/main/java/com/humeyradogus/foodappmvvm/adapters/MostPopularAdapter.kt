package com.humeyradogus.foodappmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humeyradogus.foodappmvvm.databinding.PopularItemsBinding
import com.humeyradogus.foodappmvvm.pojo.CategoryMeals
import com.humeyradogus.foodappmvvm.pojo.Meal


class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>(){
    private var mealsList: ArrayList<CategoryMeals> = ArrayList()

    fun setMealList(mealsList: ArrayList<CategoryMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    class PopularMealViewHolder(val binding: PopularItemsBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}