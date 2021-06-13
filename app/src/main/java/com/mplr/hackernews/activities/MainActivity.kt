package com.mplr.hackernews.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mplr.hackernews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.supportActionBar?.let { it.hide() }
        setContentView(binding.root)
    }
}