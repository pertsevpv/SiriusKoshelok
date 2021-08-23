package com.example.siriuskoshelok.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Currency
import com.example.siriuskoshelok.recycler.holder.CurrencyHolder

class CurrencyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyHolder(inflater.inflate(R.layout.currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curr = data[position]
        (holder as CurrencyHolder).bind(curr)
    }

    override fun getItemCount() = data.size

    fun setData(list: List<Currency>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}