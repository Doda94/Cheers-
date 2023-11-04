package com.doda.cheers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.cheers.db.CheersDatabase

class FavoritesViewModelFactory(
    val database: CheersDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}