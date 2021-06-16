package com.mplr.hackernews.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.mplr.hackernews.R
import com.mplr.hackernews.models.HitModel

class NewsHitsAdapter(
    private val dataSource: MutableList<HitModel>,
    var onListHitItemClickListener: ((hitModel: HitModel) -> Unit),
    var onDeleteItemClickListener: ((hitModel: HitModel) -> Unit)
) : RecyclerView.Adapter<NewsHitsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title) as TextView
        var author: TextView = view.findViewById(R.id.author) as TextView
        var root: ConstraintLayout = view.findViewById(R.id.container) as ConstraintLayout
        var swipe: SwipeLayout = view.findViewById(R.id.swipe_layout) as SwipeLayout
        var delete: TextView = view.findViewById(R.id.delete) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_item_news, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = dataSource[position].title ?: dataSource[position].storyTitle
        viewHolder.author.text = dataSource[position].author

        viewHolder.root.setOnClickListener {
            onListHitItemClickListener.invoke(dataSource[position])
        }

        viewHolder.delete.setOnClickListener {
            onDeleteItemClickListener.invoke(dataSource[position])
            removeAt(position)
        }
    }

    private fun removeAt(position: Int) {
        dataSource.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataSource.size)
    }

    override fun getItemCount() = dataSource.size
}