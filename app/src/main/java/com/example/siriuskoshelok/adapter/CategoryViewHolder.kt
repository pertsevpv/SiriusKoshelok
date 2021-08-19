package com.example.siriuskoshelok.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

class CategoryViewHolder(
    private val root: View
    //,private val selectListener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(root) {

    private val imgCategory: ImageView = root.findViewById(R.id.img_category)
    private val btnCategory: ImageView = root.findViewById(R.id.btn_category)
    private val nameCategory: TextView = root.findViewById(R.id.text_category)
    lateinit var item: Category

//    init {
//        btnCategory.setOnClickListener {
//            selectListener(item.name, !it.isSelected)
//        }
//    }

    fun bind(category: Category) {
        this.item = category
        nameCategory.text = item.name
        imgCategory.setImageResource(item.img)
    }

    fun bindSelectState(isSelect: Boolean) {
        if(isSelect){
            btnCategory.visibility = View.VISIBLE
        }
        else{
            btnCategory.visibility = View.INVISIBLE
        }
    }
}