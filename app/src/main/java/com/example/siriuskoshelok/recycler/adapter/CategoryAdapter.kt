package com.example.siriuskoshelok.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.OperationsDataSet.list
import com.example.siriuskoshelok.recycler.holder.CategoryHolder
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.ui.operation.CurrentOp

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Category>()

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
    private fun onClicked(item: Category, position: Int) {
        val newList = mutableListOf<Category>()
        newList.addAll(data)
        if (countItem != -1) {
            newList[countItem].isSelected = false
            newList[position].isSelected = true
            countItem = position
            CurrentOp.currentOperation?.extendedOperationType = item.name
            CurrentOp.currentOperation?.img = item.img
        } else {
            newList[position].isSelected = true
            countItem = position
            CurrentOp.currentOperation?.extendedOperationType = item.name
            CurrentOp.currentOperation?.img = item.img
        }
        setData(newList)
    }

    fun setData(new: List<Category>) {
        data.clear()
        data.addAll(new)
        notifyDataSetChanged()
    }
}