package com.doda.cheers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doda.cheers.databinding.ItemCocktailBinding
import com.doda.cheers.model.Cocktail

class CocktailsAdapter(
    private var cocktails: List<Cocktail>,
    private val onItemClickCallback: (Cocktail) -> Unit
): RecyclerView.Adapter<CocktailsAdapter.CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding)
    }

    override fun getItemCount(): Int = cocktails.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(cocktails[position])
    }

    // should fix duplicate items hopefully
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    inner class CocktailViewHolder(private val binding: ItemCocktailBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cocktail) {
            binding.cocktailName.text = item.strDrink
//            binding.cocktailDescription.text = item.strInstructions
//            there is no cocktail description in the api and instructions are too big to fit in the card and look nicely
            Glide.with(binding.cocktailPhoto.context)
                .load(item.strDrinkThumb)
                .into(binding.cocktailPhoto)
            binding.cocktailCard.setOnClickListener { onItemClickCallback(item) }
        }
    }
}