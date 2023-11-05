package com.humeyradogus.foodappmvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.humeyradogus.foodappmvvm.adapters.SearchedMealsAdapter
import com.humeyradogus.foodappmvvm.databinding.FragmentSearchBinding
import com.humeyradogus.foodappmvvm.viewModel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchRecyclerviewAdapter: SearchedMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeSearchedMealsLiveData()

        var searchJob: Job?= null
        binding.edSearch.addTextChangedListener { searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
                viewModel.searchMeals(searchQuery.toString())
            }
        }
    }

    private fun observeSearchedMealsLiveData() {
        viewModel.observeSearchedMealsLiveData().observe(viewLifecycleOwner, Observer { mealsList ->
            searchRecyclerviewAdapter.differ.submitList(mealsList)
        })
    }

    //private fun searchMeals() {
    //    val searchQuery = binding.edSearch.text.toString()
    //    if (searchQuery.isNotEmpty()){
    //        viewModel.searchMeals(searchQuery)
    //    }
    //    else{
    //        Toast.makeText(context, "Please enter a meal name to search!", Toast.LENGTH_SHORT).show()
    //    }
    //}

    private fun prepareRecyclerView() {
        searchRecyclerviewAdapter = SearchedMealsAdapter()
        binding.rvSearchedMeals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchRecyclerviewAdapter
        }
    }
}