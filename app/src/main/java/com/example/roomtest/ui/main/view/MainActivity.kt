package com.example.roomtest.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.roomtest.R
import com.example.roomtest.adapter.ProductAdapter
import com.example.roomtest.data.database.entities.ProductEntity
import com.example.roomtest.databinding.ActivityMainBinding
import com.example.roomtest.ui.AbstractActivity
import com.example.roomtest.ui.form.view.FormActivity
import com.example.roomtest.ui.main.viewmodel.MainViewModel

class MainActivity : AbstractActivity<ActivityMainBinding>(
    ActivityMainBinding::class.java
) {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(this.application)
        )[MainViewModel::class.java]
    }

    companion object {
        fun create(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }

    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpToolbarTitle(R.string.products_list)
        onExecute()
    }

    private fun onExecute() {
        setPermissions()
        initObservers()
        initAdapter()
        initListeners()
    }

    override fun onRestart() {
        super.onRestart()
        onExecute()
    }

    private fun initAdapter() {
        binding.rvProducts.adapter = adapter
    }

    override fun initListeners() {
        binding.fabAdd.setOnClickListener {
            startForm()
        }

        adapter.setOnItemClickListener {
            if (it is ProductEntity) {
                updateStartForm(it)
            }
        }

        adapter.setOnDeleteClickListener {
            if (it is ProductEntity) {
                dialog.questionDialog("¿Deseas borrar este producto?") {
                    viewModel.deleteProduct(it)
                    initObservers()
                }
            }
        }
    }

    private fun startForm() = startActivity(FormActivity.create(this))

    private fun updateStartForm(item: ProductEntity) =
        startActivity(FormActivity.create(this).apply {
            putExtra("sku", item.sku)
            putExtra("price", item.price)
            putExtra("description", item.description)
            putExtra("image", item.image)
        })

    override fun initObservers() {
        viewModel.getAllProducts().observe(this) {
            binding.tvNoProducts.isVisible = it.isEmpty()
            adapter.dataList = it
        }
    }


}