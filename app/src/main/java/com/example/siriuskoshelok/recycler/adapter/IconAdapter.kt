package com.example.siriuskoshelok.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Icon
import com.example.siriuskoshelok.recycler.holder.IconHolder

class IconAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Icon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        val viewHolder = IconHolder(rootView)
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION)
                onClicked(data[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as IconHolder).bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    private var countItem = -1
    private fun onClicked(item: Icon, position: Int) {
        val newList = mutableListOf<Icon>()
        newList.addAll(data)
        if (countItem != -1) {
            newList[countItem].isSelect = false
            newList[position].isSelect = true
            countItem = position
        } else {
            newList[position].isSelect = true
            countItem = position
        }
        setData(newList)
    }

    fun getPosDraw(): Int{
        return countItem
    }

    fun setData(new: List<Icon>) {
        data.clear()
        data.addAll(new)
        notifyDataSetChanged()
    }
}