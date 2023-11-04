package com.doda.cheers.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromFavoriteCocktailToString(cocktail: FavoriteCocktail): String {
        return gson.toJson(cocktail)
    }

    @TypeConverter
    fun fromStringToFavoriteCocktail(cocktail: String): FavoriteCocktail {
        return gson.fromJson(cocktail, FavoriteCocktail::class.java)
    }

    @TypeConverter
    fun fromLocalCocktailToString(cocktail: LocalCocktail): String {
        return gson.toJson(cocktail)
    }

    @TypeConverter
    fun fromStringToLocalCocktail(cocktail: String): LocalCocktail {
        return gson.fromJson(cocktail, LocalCocktail::class.java)
    }

}