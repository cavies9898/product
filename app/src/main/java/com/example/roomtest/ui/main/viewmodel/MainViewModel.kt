package com.example.roomtest.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomtest.data.Repository
import com.example.roomtest.data.database.entities.ProductEntity
import kotlinx.coroutines.launch

class MainViewModel(application: Application): ViewModel() {

    private val productRepository: Repository = Repository(application)

    fun insertProduct(product: ProductEntity) = viewModelScope.launch {
        productRepository.insertProduct(product)
    }

    fun updateProduct(product: ProductEntity) = viewModelScope.launch {
        productRepository.updateProduct(product)
    }

    fun deleteProduct(product: ProductEntity) = viewModelScope.launch {
        productRepository.deleteProduct(product)
    }

    fun getAllProducts() : LiveData<List<ProductEntity>> = productRepository.getAllProducts()


     class  MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
         override fun <T : ViewModel> create(modelClass: Class<T>): T {
             if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                 return  MainViewModel(application) as T
             }

             throw IllegalArgumentException("Unable construct viewModel")
         }
     }
}