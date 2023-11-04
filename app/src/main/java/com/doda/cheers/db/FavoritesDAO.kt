package com.doda.cheers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDAO {

    @Query("SELECT * FROM favorite_cocktails")
    fun getAllFavorites(): List<FavoriteCocktail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktail: FavoriteCocktail)
}