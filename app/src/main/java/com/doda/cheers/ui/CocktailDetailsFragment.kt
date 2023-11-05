package com.doda.cheers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.doda.cheers.CheersApplication
import com.doda.cheers.R
import com.doda.cheers.databinding.FragmentCocktailDetailsBinding
import com.doda.cheers.db.FavoriteCocktail
import com.doda.cheers.model.Cocktail
import com.doda.cheers.model.SimpleCocktail
import com.doda.cheers.viewModel.CocktailsViewModel
import com.doda.cheers.viewModel.FavoritesViewModel
import com.doda.cheers.viewModel.FavoritesViewModelFactory
import java.net.URL

class CocktailDetailsFragment : Fragment() {

    private var _binding: FragmentCocktailDetailsBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<CocktailDetailsFragmentArgs>()

    val viewModel: FavoritesViewModel by viewModels{
        FavoritesViewModelFactory((activity?.application as CheersApplication).database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addUiInfo()
        binding.floatingActionButton.setOnClickListener {
            val id = args.cocktail.id
            val name = args.cocktail.name
            val instructions = args.cocktail.instructions
            val ingredients = args.cocktail.ingredients
            val imgSrcUrl = args.cocktail.imgSrcUrl
            viewModel.insertFavorite(FavoriteCocktail(id, name, instructions, ingredients, imgSrcUrl))
        }

        hideNavBar()

    }

    fun hideNavBar() {
        val navBar = activity?.findViewById<View>(R.id.bottom_nav_view)
        navBar?.visibility = View.GONE
    }

    fun addUiInfo() {
        binding.cocktailName.text = args.cocktail.name
        binding.cocktailInstructions.text = args.cocktail.instructions
        Glide.with(requireContext()).load(args.cocktail.imgSrcUrl).into(binding.cocktailPhoto)
        binding.cocktailIngredients.text = args.cocktail.ingredients
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}