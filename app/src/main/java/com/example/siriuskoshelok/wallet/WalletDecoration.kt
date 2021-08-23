package com.example.siriuskoshelok.wallet

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.Constants

@Suppress("MagicNumber")
class WalletDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = Constants.WALLET_OUT_RECT_BOTTOM
        outRect.top = Constants.WALLET_OUT_RECT_TOP
        outRect.left = Constants.WALLET_OUT_RECT_LEFT
    }
}
