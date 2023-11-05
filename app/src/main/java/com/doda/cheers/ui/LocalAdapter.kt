package com.doda.cheers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doda.cheers.databinding.ItemCocktailBinding
import com.doda.cheers.db.LocalCocktail

class LocalAdapter(
    private var localCocktails: List<LocalCocktail>,
    private val onLocalClick: (LocalCocktail) -> Unit
) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LocalCocktail) {
            binding.cocktailName.text = item.name
            Glide.with(binding.cocktailPhoto.context)
                .load(item.imgResourceId)
                .skipMemoryCache(true)
                .placeholder(com.doda.cheers.R.drawable.no_image)
                .into(binding.cocktailPhoto)
            binding.cocktailCard.setOnClickListener { onLocalClick(item) }
        }
    }

    override fun getItemCount(): Int = localCocktails.size
    override fun getItemId(position: Int) = position.toLong()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(localCocktails[position])
    }

    override fun getItemViewType(position: Int) = position

}