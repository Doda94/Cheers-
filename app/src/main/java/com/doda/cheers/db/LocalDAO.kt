package com.doda.cheers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalDAO {

    @Query("SELECT * FROM local_cocktails")
    fun getAllCocktails(): List<LocalCocktail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktail: LocalCocktail)

}