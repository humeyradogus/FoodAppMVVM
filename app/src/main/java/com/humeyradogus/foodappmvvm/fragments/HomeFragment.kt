package com.humeyradogus.foodappmvvm.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.humeyradogus.foodappmvvm.databinding.FragmentHomeBinding
import com.humeyradogus.foodappmvvm.viewModel.HomeViewmodel
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.humeyradogus.foodappmvvm.activities.MealActivity
import com.humeyradogus.foodappmvvm.pojo.Meal

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewmodel
    private lateinit var randomMeal: Meal

    companion object{
        const val MEAL_ID = "com.humeyradogus.foodappmvvm.fragments.idMeal"
        const val MEAL_NAME = "com.humeyradogus.foodappmvvm.fragments.nameMeal"
        const val MEAL_THUMB = "com.humeyradogus.foodappmvvm.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this).get(HomeViewmodel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

    }

    private fun onRandomMealClick() {
        binding.imgRandomMealCard.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)

            this.randomMeal = meal
        }
    }

}