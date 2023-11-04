package com.doda.cheers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doda.cheers.CheersApplication
import com.doda.cheers.databinding.FragmentFavoritesBinding
import com.doda.cheers.db.CheersDatabase
import com.doda.cheers.db.FavoriteCocktail
import com.doda.cheers.model.SimpleCocktail
import com.doda.cheers.viewModel.FavoritesViewModel
import com.doda.cheers.viewModel.FavoritesViewModelFactory

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    val viewModel: FavoritesViewModel by viewModels{
        FavoritesViewModelFactory((activity?.application as CheersApplication).database)
    }

    private lateinit var adapter: FavoritesAdapter

    private var favorites: List<FavoriteCocktail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFavoritesList()
        viewModel.getFavorites()

    }

    fun observeFavoritesList() {
        viewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            favorites = it
            initRecyclerView(favorites)
        }
    }

    fun initRecyclerView(favoritesList: List<FavoriteCocktail>){
        adapter = FavoritesAdapter(favoritesList) { favoriteCocktail ->
            val id = favoriteCocktail.id
            val name = favoriteCocktail.name
            val instructions = favoriteCocktail.instructions
            val ingredients = favoriteCocktail.ingredients
            val imgSrcUrl = favoriteCocktail.imgSrcUrl
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToCocktailDetailsFragment(SimpleCocktail(id, name, instructions, ingredients, imgSrcUrl))
            findNavController().navigate(action)
        }
        binding.favoritesRecyclerView.adapter = adapter

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}