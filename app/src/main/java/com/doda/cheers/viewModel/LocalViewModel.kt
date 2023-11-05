package com.doda.cheers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.cheers.db.CheersDatabase
import com.doda.cheers.db.LocalCocktail
import java.util.concurrent.Executors

class LocalViewModel(
    private val database: CheersDatabase
) : ViewModel() {

    private var _localLiveData = MutableLiveData<List<LocalCocktail>>(listOf())

    val localLiveData: MutableLiveData<List<LocalCocktail>> = _localLiveData

    fun getLocals() {
        Executors.newSingleThreadExecutor().execute {
            _localLiveData.postValue(database.localDAO().getAllCocktails())
        }
    }

    fun insertLocal(cocktail: LocalCocktail) {
        Executors.newSingleThreadExecutor().execute {
            database.localDAO().insertCocktail(cocktail)
            getLocals()
        }
    }

}