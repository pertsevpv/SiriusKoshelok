package com.example.siriuskoshelok.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.siriuskoshelok.entity.Category

class ItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Category, newItem: Category): Any? {
        return if (oldItem.isSelected != newItem.isSelected) true else null
    }
}