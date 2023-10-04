package com.example.roomtest.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomtest.data.database.ProductDB
import com.example.roomtest.data.database.dao.ProductDao
import com.example.roomtest.data.database.entities.ProductEntity

class Repository(app: Application) {
    private val productDao: ProductDao

    init {
        val productDB : ProductDB = ProductDB.getInstance(app)
        productDao = productDB.getProductDao()
    }

    suspend fun insertProduct(product: ProductEntity) = productDao.insertProduct(product)
    suspend fun updateProduct(product: ProductEntity) = productDao.updateProduct(product)
    suspend fun deleteProduct(product: ProductEntity) = productDao.deleteProduct(product)

    fun getAllProducts(): LiveData<List<ProductEntity>> = productDao.getAllProducts()
}