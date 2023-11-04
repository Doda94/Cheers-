package com.doda.cheers

import android.app.Application
import com.doda.cheers.db.CheersDatabase
import com.google.android.material.color.DynamicColors

class CheersApplication : Application() {

    val database by lazy {
        CheersDatabase.getDatabase(this)
    }

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}