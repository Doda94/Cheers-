package com.doda.cheers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.cheers.db.CheersDatabase
import com.doda.cheers.db.FavoriteCocktail
import java.util.concurrent.Executors

class FavoritesViewModel(
    private val database: CheersDatabase
) : ViewModel() {

    private var _favoritesLiveData = MutableLiveData<List<FavoriteCocktail>>(listOf())

    val favoritesLiveData: MutableLiveData<List<FavoriteCocktail>> = _favoritesLiveData

    fun getFavorites() {
        Executors.newSingleThreadExecutor().execute {
            _favoritesLiveData.postValue(database.favoritesDAO().getAllFavorites())
        }
    }

    fun insertFavorite(cocktail: FavoriteCocktail) {
        Executors.newSingleThreadExecutor().execute {
            database.favoritesDAO().insertCocktail(cocktail)
        }
    }

}