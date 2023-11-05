package com.doda.cheers.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_cocktails")
data class LocalCocktail(
    @PrimaryKey val id: String,
    val name: String?,
    val instructions: String?,
    val ingredients: String?,
    val imgResourceId: String?,
)
