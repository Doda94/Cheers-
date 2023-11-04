package com.doda.cheers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doda.cheers.R
import com.doda.cheers.databinding.ItemCocktailBinding
import com.doda.cheers.db.FavoriteCocktail

class FavoritesAdapter(
    private var favorites: List<FavoriteCocktail>,
    private val onFavoriteClick: (FavoriteCocktail) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    fun updateFavorites(favorites: List<FavoriteCocktail>) {
        this.favorites = favorites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    inner class FavoritesViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteCocktail) {
            binding.cocktailName.text = item.name
            Glide.with(binding.cocktailPhoto.context)
                .load(item.imgSrcUrl)
                .placeholder(R.drawable.no_image)
                .into(binding.cocktailPhoto)
            binding.cocktailCard.setOnClickListener { onFavoriteClick(item) }
        }
    }

    override fun getItemCount(): Int = favorites.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

}