package com.example.siriuskoshelok.recycler.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.utils.Constants

@Suppress("MagicNumber")
class OperationDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = Constants.OPERATION_OUT_RECT_BOTTOM
        outRect.top = Constants.OPERATION_OUT_RECT_TOP
        outRect.left = Constants.OPERATION_OUT_RECT_LEFT
    }
}
