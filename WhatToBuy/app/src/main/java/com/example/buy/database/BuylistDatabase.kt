package com.example.buy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buy.model.Model

@Database(entities = [Model::class],version = 1,exportSchema = false)
abstract class BuylistDatabase:RoomDatabase() {
    abstract fun buylistDao(): BuylistDao

    companion object {
        @Volatile
        private var INSTANCE: BuylistDatabase? = null
        fun getDatabase(context: Context): BuylistDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BuylistDatabase::class.java,
                    "buylistDB"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
