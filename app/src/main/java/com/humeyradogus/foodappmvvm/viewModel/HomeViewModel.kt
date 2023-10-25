package com.humeyradogus.foodappmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humeyradogus.foodappmvvm.pojo.CategoryList
import com.humeyradogus.foodappmvvm.pojo.CategoryMeals
import com.humeyradogus.foodappmvvm.pojo.Meal
import com.humeyradogus.foodappmvvm.pojo.MealList
import com.humeyradogus.foodappmvvm.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()

    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object: Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null){
                    randomMealLiveData.value = response.body()!!.meals[0]
                    //Log.d("TEST","Meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object: Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                    //Log.d("TEST","Meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })
    }

    fun observeRandomMealLiveData():LiveData<Meal>{
        // live data cannot be changed that's why we return with this func
        return randomMealLiveData
    }

    fun observePopularItemsLiveData():LiveData<List<CategoryMeals>>{
        // live data cannot be changed that's why we return with this func
        return popularItemsLiveData
    }
}