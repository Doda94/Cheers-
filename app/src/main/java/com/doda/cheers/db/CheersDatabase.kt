package com.doda.cheers.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        FavoriteCocktail::class,
        LocalCocktail::class
    ], version = 3
)
@TypeConverters(Converters::class)
abstract class CheersDatabase : RoomDatabase() {

    abstract fun favoritesDAO(): FavoritesDAO

    abstract fun localDAO(): LocalDAO

    companion object {

        private var INSTANCE: CheersDatabase? = null

        fun getDatabase(context: Context): CheersDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(context, CheersDatabase::class.java, "cheers_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = database
                database
            }
        }
    }
}