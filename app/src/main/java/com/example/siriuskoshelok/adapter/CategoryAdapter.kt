package com.example.siriuskoshelok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

class CategoryAdapter(//private val selectListener: (String, Boolean) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        //val viewHolder = CategoryViewHolder(rootView, selectListener)
        return CategoryViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            (holder as CategoryViewHolder).bindSelectState(data[position].isSelected)
        }

    }

    override fun getItemCount() = data.size

    fun setData(new: List<Category>) {
        data.clear()
        data.addAll(new)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(data[position])
    }
}