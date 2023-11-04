package com.doda.cheers.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cocktails")
data class FavoriteCocktail(
    @PrimaryKey val id: String,
    val name: String?,
    val instructions: String?,
    val ingredients: String?,
    val imgSrcUrl: String?,
)
