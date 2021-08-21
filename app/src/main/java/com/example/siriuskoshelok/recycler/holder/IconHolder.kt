package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R

class IconHolder(
    private val root: View
) : RecyclerView.ViewHolder(root) {

    private val imgIcon: ImageView = root.findViewById(R.id.icon)

    fun bind(icon: Int){
        imgIcon.setImageResource(icon)
    }
}