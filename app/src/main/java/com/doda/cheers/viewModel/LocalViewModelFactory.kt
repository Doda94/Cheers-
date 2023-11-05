package com.doda.cheers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.cheers.db.CheersDatabase

class LocalViewModelFactory(
    val database: CheersDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocalViewModel::class.java)) {
            return LocalViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}