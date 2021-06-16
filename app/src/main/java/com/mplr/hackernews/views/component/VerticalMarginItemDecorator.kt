package com.mplr.hackernews.views.component

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mplr.hackernews.R

class VerticalMarginItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = context.resources.getDimension(R.dimen.spacing_xmedium).toInt()
            }
            bottom = context.resources.getDimension(R.dimen.spacing_xmedium).toInt()
        }
    }
}