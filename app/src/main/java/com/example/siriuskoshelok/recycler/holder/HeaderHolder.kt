package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.dayAndMonth
import java.util.*

class HeaderHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    private val headerText: TextView = root.findViewById(R.id.header_date)

    // FIXME: 18.08.2021 use decorations and string resources
    fun bind(date: String) {
        headerText.text = when (date) {
            Date().dayAndMonth() -> "Today"
            Date(System.currentTimeMillis() - 86400 * 1000L).dayAndMonth() -> "Yesterday"
            else -> date
        }
    }
}
