package com.mplr.hackernews.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.mplr.hackernews.R
import com.mplr.hackernews.databinding.ActivityMainBinding
import com.mplr.hackernews.models.HitModel
import com.mplr.hackernews.models.NewsHitsModel
import com.mplr.hackernews.viewmodel.NewsHitsViewModel
import com.mplr.hackernews.views.adapter.NewsHitsAdapter
import com.mplr.hackernews.views.component.NewsDetailBottomSheet
import com.mplr.hackernews.views.component.VerticalMarginItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    private val viewModel: NewsHitsViewModel by viewModels()

    private val listNewsHitsObserver = Observer<NewsHitsModel> { newsHits ->
        binding?.progressBar?.visibility = View.GONE
        binding?.swipeRefresh?.isRefreshing = false

        if (newsHits.hits.size > 0) {

            val adapter = NewsHitsAdapter(
                newsHits.hits,
                onListHitItemClickListener,
                onDeleteItemClickListener
            )
            binding?.listNewsHits?.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
        }

    }

    private var onListHitItemClickListener: ((hitModel: HitModel) -> Unit) =
        { hitModel ->
            NewsDetailBottomSheet.newInstance(hitModel).show(supportFragmentManager, "")
        }

    private var onDeleteItemClickListener: ((hitModel: HitModel) -> Unit) =
        { hitModel ->
            viewModel.deleteItemDataBase(hitModel.objectId)
            Toast.makeText(this, R.string.item_delete, Toast.LENGTH_LONG).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.supportActionBar?.let { it.hide() }
        setContentView(binding?.root)
        initRecyclerView()
        initObservers()
        initListener()
        progressVisible()
        viewModel.getHits()
    }

    private fun progressVisible() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.swipeRefresh?.isRefreshing = true
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        binding?.listNewsHits?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            addItemDecoration(VerticalMarginItemDecorator(context))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun initListener() {
        binding?.swipeRefresh?.setOnRefreshListener(OnRefreshListener
        {
            binding?.swipeRefresh?.isRefreshing = true
            viewModel.getHits()
        })
    }

    private fun initObservers() {
        viewModel.listNewsHits.observe(this, listNewsHitsObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}