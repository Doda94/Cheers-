package com.doda.cheers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.cheers.R
import com.doda.cheers.api.ApiModule
import com.doda.cheers.databinding.FragmentHomeBinding
import com.doda.cheers.model.Cocktail
import com.doda.cheers.model.SimpleCocktail
import com.doda.cheers.viewModel.CocktailsViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailsViewModel by viewModels()

    private lateinit var adapter: CocktailsAdapter

    private var cocktails: List<Cocktail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeRandomCocktailsList()
        ApiModule.initRetrofit()
        for (i in 1..5){
            viewModel.addRandomCocktail()
        }
        initSearchView()
        showNavBar()

    }

    private fun showNavBar() {
        val navBar = activity?.findViewById<View>(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView(cocktailsList: List<Cocktail>) {
        adapter = CocktailsAdapter(cocktailsList) { cocktail ->
            val id = cocktail.idDrink
            val name = cocktail.strDrink
            val instructions = cocktail.strInstructions
            val ingredients = viewModel.getIngridients(cocktail)
            val imgSrcUrl = cocktail.strDrinkThumb
            val action = HomeFragmentDirections.actionNavigationHomeToCocktailDetailsFragment(SimpleCocktail(id, name, instructions, ingredients, imgSrcUrl))
            findNavController().navigate(action)
        }
        binding.cocktailsHomeRecyclerView.adapter = adapter

        binding.cocktailsHomeRecyclerView.layoutManager = LinearLayoutManager(activity)

    }

    fun observeRandomCocktailsList() {
        viewModel.randomCocktailsLiveData.observe(viewLifecycleOwner) { items ->
            cocktails += items
            initRecyclerView(cocktails)
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cocktails = listOf()
                viewModel.searchCocktail(query!!)
                adapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cocktails = listOf()
                viewModel.searchCocktail(newText!!)
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }

}