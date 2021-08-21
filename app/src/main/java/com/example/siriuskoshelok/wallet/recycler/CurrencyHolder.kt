package com.example.siriuskoshelok.wallet.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Currency
import kotlin.random.Random

class CurrencyHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val currName: TextView = root.findViewById(R.id.curr_name)
    private val currMoney: TextView = root.findViewById(R.id.curr_money)
    private val strelochka: ImageView = root.findViewById(R.id.strelochka)

    fun bind(curr: Currency) {
        currName.text = curr.name
        currMoney.text = String.format("%.2f", curr.money)
        strelochka.setImageResource(
            if (Random.nextBoolean())
                R.drawable.ic_curr_down
            else
                R.drawable.ic_curr_up
        )
    }

}