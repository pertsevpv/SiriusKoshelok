package com.example.siriuskoshelok.wallet

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WalletDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 24
        outRect.top = 32
        outRect.left = 16
    }
}