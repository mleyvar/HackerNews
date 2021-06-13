package com.mplr.hackernews.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mplr.hackernews.R
import com.mplr.hackernews.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_HackerNews)
        super.onCreate(savedInstanceState)
        this.supportActionBar?.let { it.hide() }
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLottieAnimation()
    }

    private fun startLottieAnimation() {
        binding.animationStartLottie.addAnimatorUpdateListener { valueAnimator ->
            val progress = (valueAnimator.animatedValue as Float * 100).toInt()

            if (progress >= 95) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        binding.animationStartLottie.playAnimation()
    }
}