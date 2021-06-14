package com.mplr.hackernews.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mplr.hackernews.databinding.ActivityMainBinding
import com.mplr.hackernews.viewmodel.NewsHitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: NewsHitsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.supportActionBar?.let { it.hide() }
        setContentView(binding.root)

        viewModel.getHits()
    }
}