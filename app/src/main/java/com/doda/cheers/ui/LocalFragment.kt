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
import com.doda.cheers.R
import com.doda.cheers.databinding.FragmentLocalBinding
import com.doda.cheers.db.LocalCocktail
import com.doda.cheers.model.SimpleCocktail
import com.doda.cheers.viewModel.LocalViewModel
import com.doda.cheers.viewModel.LocalViewModelFactory

class LocalFragment : Fragment() {

    private var _binding: FragmentLocalBinding? = null

    private val binding get() = _binding!!

    val viewModel: LocalViewModel by viewModels {
        LocalViewModelFactory((activity?.application as CheersApplication).database)
    }

    private lateinit var adapter: LocalAdapter

    private var localCocktails: List<LocalCocktail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLocalList()
        viewModel.getLocals()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_local_to_addCocktailBottomSheetFragment)
        }

        showNavBar()
    }

    private fun observeLocalList() {
        viewModel.localLiveData.observe(viewLifecycleOwner) {
            localCocktails = it
            initRecyclerView(localCocktails)
        }
    }

    private fun initRecyclerView(localList: List<LocalCocktail>) {
        adapter = LocalAdapter(localList) { localCocktail ->
            val id = localCocktail.id
            val name = localCocktail.name
            val instructions = localCocktail.instructions
            val ingredients = localCocktail.ingredients
            val image = localCocktail.imgResourceId
            val action = LocalFragmentDirections.actionLocalFragmentToCocktailDetailsFragment(
                SimpleCocktail(
                    id,
                    name,
                    instructions,
                    ingredients,
                    image.toString()
                )
            )
            findNavController().navigate(action)
        }
        binding.localRecyclerView.adapter = adapter

        binding.localRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun showNavBar() {
        val navBar = activity?.findViewById<View>(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocals()
        adapter.notifyDataSetChanged()
    }

}