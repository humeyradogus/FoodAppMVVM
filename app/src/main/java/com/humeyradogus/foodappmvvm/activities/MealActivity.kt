package com.humeyradogus.foodappmvvm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.humeyradogus.foodappmvvm.R
import com.humeyradogus.foodappmvvm.databinding.ActivityMealBinding
import com.humeyradogus.foodappmvvm.fragments.HomeFragment
import com.humeyradogus.foodappmvvm.pojo.Meal
import com.humeyradogus.foodappmvvm.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink: String
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]

        loadingCase()
        getMealInformationFromIntent()
        setInformationInViews()

        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                val meal = t

                onResponseCase()

                binding.tvCategory.text = "Category: ${meal!!.strMeal}"
                binding.tvArea.text = "Area: ${meal!!.strArea}"
                binding.tvInstructionsSteps.text = meal!!.strInstructions

                youtubeLink = meal.strYoutube
            }

        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}