package com.humeyradogus.foodappmvvm.retrofit

import com.humeyradogus.foodappmvvm.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}