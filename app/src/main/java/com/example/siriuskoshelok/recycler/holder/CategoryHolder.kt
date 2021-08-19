package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

class CategoryHolder(
    private val root: View
) : RecyclerView.ViewHolder(root) {

    private val imgCategory: ImageView = root.findViewById(R.id.img_category)
    private val btnCategory: ImageView = root.findViewById(R.id.btn_category)
    private val nameCategory: TextView = root.findViewById(R.id.text_category)
    lateinit var item: Category

    fun bind(category: Category) {
        this.item = category
        nameCategory.text = item.name
        imgCategory.setImageResource(item.img)
        if (item.isSelected) {
            btnCategory.visibility = View.VISIBLE
        } else {
            btnCategory.visibility = View.INVISIBLE
        }
    }
}