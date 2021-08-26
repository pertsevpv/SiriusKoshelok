package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.recycler.items.CategoryItem

class CategoryHolder(
    private val root: View
) : RecyclerView.ViewHolder(root) {

    private val imgCategory: ImageView = root.findViewById(R.id.img_wallet)
    private val btnCategory: ImageView = root.findViewById(R.id.btn_category)
    private val nameCategory: TextView = root.findViewById(R.id.wallet_name_text)
    lateinit var item: CategoryItem

    fun bind(category: CategoryItem) {
        this.item = category
        nameCategory.text = item.category.name
        imgCategory.setImageResource(/*item.category.pictureId ?: */R.drawable.dot_green)
        btnCategory.isVisible = item.isSelected
    }
}
