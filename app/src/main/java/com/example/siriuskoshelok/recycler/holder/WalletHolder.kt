package com.example.siriuskoshelok.recycler.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Wallet

class WalletHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val walletName: TextView = root.findViewById(R.id.wallet_name)
    private val balance: TextView = root.findViewById(R.id.wallet_balance)
    private val img: ImageView = root.findViewById(R.id.img_wallet)
    private val limit: ImageView = root.findViewById(R.id.error_limit)

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(wal: Wallet) {
        img.setImageResource(R.drawable.ic_wallet)
        walletName.text = wal.name
        val money = wal.countMoney()
        val expense = wal.countExpense()
        balance.text = "$money ₽"
        if (wal.limit != null && expense > wal.limit!!)
            limit.visibility = View.VISIBLE
        else
            limit.visibility = View.GONE
    }
}