package com.example.roomtest.ui.splash.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import com.example.roomtest.R
import com.example.roomtest.databinding.ActivitySplashScreenBinding
import com.example.roomtest.ui.main.view.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLottieAnimation()
    }

    private fun initLottieAnimation() {
        val handlerThread = HandlerThread("SplashThread")
        handlerThread.start()

        val handler = Handler(handlerThread.looper)

        handler.postDelayed({
            startMain()
        }, 2500)
    }

    private fun startMain() {
        startActivity(MainActivity.create(this))
        finish()
    }

}