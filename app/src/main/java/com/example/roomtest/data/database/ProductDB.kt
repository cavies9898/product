package com.example.roomtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomtest.data.database.dao.ProductDao
import com.example.roomtest.data.database.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDB : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var instance: ProductDB? = null

        fun getInstance(context: Context): ProductDB {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, ProductDB::class.java, "product_database").build()
            }
            return instance!!
        }

    }
}
