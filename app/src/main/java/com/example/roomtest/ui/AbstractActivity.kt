package com.example.roomtest.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.roomtest.R
import com.example.roomtest.core.Dialogs
import com.example.roomtest.core.ViewHelper
import com.google.android.material.appbar.MaterialToolbar


const val REQUEST_PERMISSION_CODE = 77

abstract class AbstractActivity<V : ViewBinding>(
    private val viewBinding: Class<V>
) : AppCompatActivity() {


    protected val dialog by lazy {
        Dialogs(this)
    }

    protected val viewHelper by lazy {
        ViewHelper()
    }

    protected lateinit var binding: V

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val method = viewBinding.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(viewBinding, layoutInflater) as V
        val view = binding.root
        setContentView(view)

        /** MaterialToolBar */
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar?.let { materialToolbar ->
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
            materialToolbar.setNavigationOnClickListener {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initListeners()
        initObservers()
    }


    protected abstract fun initListeners()
    protected open fun initObservers() {}


    fun setPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    dialog.infoDialog(getString(com.example.roomtest.R.string.accept_permissions)) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    protected fun setUpToolbarTitle(title: Int) {
        val tvTitle: TextView? = findViewById(R.id.tv_toolbar_title)
        tvTitle?.setText(title)
    }

    protected fun setUpCommonToolbarTitle(title: Int) {
        val toolbar: MaterialToolbar? = findViewById(R.id.topAppBar)
        toolbar?.setTitle(title)
    }

}


