package com.example.siriuskoshelok.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.recycler.holder.CategoryHolder
import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.ui.operation.CurrentOperation

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<CategoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        val viewHolder = CategoryHolder(rootView)
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION)
                onClicked(data[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryHolder).bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    private var countItem = -1
    private fun onClicked(item: CategoryItem, position: Int) {
        //val newList = mutableListOf<CategoryItem>()
        //newList.addAll(data)
        if (countItem != -1) {
            data[countItem].isSelected = false
            data[position].isSelected = true
            countItem = position
            CurrentOperation.instanse?.categoryId = item.category.id
        } else {
            data[position].isSelected = true
            countItem = position
            CurrentOperation.instanse?.categoryId = item.category.id
        }
        notifyDataSetChanged()
        //setData(newList)
    }

    fun setData(new: List<CategoryItem>) {
        data.clear()
        data.addAll(new)
        notifyDataSetChanged()
    }
}
