package com.example.siriuskoshelok.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.utils.dayAndMonth
import com.example.siriuskoshelok.utils.dayMonthYear
import java.util.*

class HeaderHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    private val headerText: TextView = root.findViewById(R.id.header_date)

    fun bind(date: GregorianCalendar) {
        headerText.text = when (date.dayMonthYear()) {
            GregorianCalendar().dayMonthYear() -> "Сегодня"
            GregorianCalendar().dayMonthYear()
                .apply { add(Calendar.DAY_OF_MONTH, -1) } -> "Вчера"
            else -> date.dayAndMonth()
        }
    }
}
