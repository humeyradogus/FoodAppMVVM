package com.humeyradogus.foodappmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humeyradogus.foodappmvvm.pojo.Meal
import com.humeyradogus.foodappmvvm.pojo.MealList
import com.humeyradogus.foodappmvvm.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel()  {
    private var searchedMealsLiveData = MutableLiveData<List<Meal>>()

    fun searchMeals(searchQuery: String) = RetrofitInstance.api.searchMeals(searchQuery).enqueue(object : Callback<MealList>{
        override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
            val mealsList = response.body()?.meals
            mealsList.let {
                searchedMealsLiveData.postValue(it)
            }
        }

        override fun onFailure(call: Call<MealList>, t: Throwable) {
            Log.d("SearchViewModel",t.message.toString())
        }
    })

    fun observeSearchedMealsLiveData(): LiveData<List<Meal>> = searchedMealsLiveData
}