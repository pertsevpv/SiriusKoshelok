package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Icon

class IconHolder(
    private val root: View
) : RecyclerView.ViewHolder(root) {

    private val imgIcon: ImageView = root.findViewById(R.id.icon)
    private val select: ImageView = root.findViewById(R.id.icon_select)

    fun bind(item: Icon){
        imgIcon.setImageResource(item.img)
        select.isVisible = item.isSelect
    }
}