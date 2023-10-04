package com.example.roomtest.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomtest.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products_table")
    fun getAllProducts():LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM products_table WHERE sku = :sku")
    fun getProductBySku(sku: Int): ProductEntity?
}