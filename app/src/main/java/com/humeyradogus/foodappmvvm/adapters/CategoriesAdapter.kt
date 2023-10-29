package com.humeyradogus.foodappmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humeyradogus.foodappmvvm.databinding.CategoryItemBinding
import com.humeyradogus.foodappmvvm.databinding.PopularItemsBinding
import com.humeyradogus.foodappmvvm.pojo.Category
import com.humeyradogus.foodappmvvm.pojo.CategoryList

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    private var categoriesList = ArrayList<Category>()

    fun setCategoryList(categoriesList:List<Category>){
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

}