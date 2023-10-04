package com.example.roomtest.ui.form.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.roomtest.R
import com.example.roomtest.core.ViewHelper.Companion.setFieldError
import com.example.roomtest.data.database.entities.ProductEntity
import com.example.roomtest.databinding.ActivityFormBinding
import com.example.roomtest.ui.AbstractActivity
import com.example.roomtest.ui.form.viewmodel.FormViewModel
import com.github.dhaval2404.imagepicker.ImagePicker

class FormActivity : AbstractActivity<ActivityFormBinding>(
    ActivityFormBinding::class.java
) {

    private val viewModel: FormViewModel by lazy {
        ViewModelProvider(
            this,
            FormViewModel.FormViewModelFactory(this.application)
        )[FormViewModel::class.java]
    }

    companion object {
        fun create(context: Context): Intent =
            Intent(context, FormActivity::class.java)
    }

    private var image: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpCommonToolbarTitle(R.string.title_form)
        setProduct()
        initListeners()
    }

    private fun setProduct() {
        val sku = intent.getStringExtra("sku")
        val price = intent.getDoubleExtra("price", 0.0)
        val description = intent.getStringExtra("description")
        image = intent.getStringExtra("image")
        if (sku != null && price != null && description != null && image != null) {
            binding.tietSku.isEnabled = false
            binding.tietSku.setText(sku)
            binding.tietPrice.setText(price.toString())
            binding.tietDescription.setText(description)
            Glide.with(this)
                .load(image!!.toUri())
                .fitCenter()
                .into(binding.sivAddPhoto)
        }
    }


    override fun initListeners() {

        with(binding) {
            btnSave.setOnClickListener {
                val sku = tietSku.text
                val price = tietPrice.text.toString().toDoubleOrNull()
                val description = tietDescription.text.toString()

                when {
                    sku.isNullOrEmpty() -> tietSku.setFieldError(getString(R.string.empty_sku))
                    sku.length != 16 -> tietSku.setFieldError(getString(R.string.sku_less_characters))
                    price == null || price <= 0.0 -> tietPrice.setFieldError(getString(R.string.empty_price))
                    description.isNullOrEmpty() -> tietDescription.setFieldError(getString(R.string.empty_description))
                    image == null -> dialog.infoDialog(getString(R.string.take_photo))
                    else -> insertProduct()
                }
            }

            sivAddPhoto.setOnClickListener {
                setUpImagePicker()
            }
        }

    }


    private fun insertProduct() {
        val sku = binding.tietSku.text
        val price = binding.tietPrice.text.toString().toDouble()
        val description = binding.tietDescription.text.toString()

        val product = ProductEntity(
            sku = sku.toString(),
            price = price,
            description = description,
            image = image.toString()
        )
        viewModel.insertProduct(product)
        dialog.infoDialog(getString(R.string.product_registered)) {
            finish()
        }
    }

    private fun setUpImagePicker() {
        ImagePicker.with(this)
            .compress(1024)
            .crop()
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data!!

                    image = fileUri.toString()
                    binding.sivAddPhoto.setImageURI(fileUri)
                }

                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.do_not_capture_image), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

}