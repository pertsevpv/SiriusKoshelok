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
    private val img : ImageView = root.findViewById(R.id.img_wallet)

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(wal: Wallet) {
        img.setImageResource(R.drawable.ic_wallet)
        walletName.text = wal.name
        balance.text = "${wal.countMoney()} $"
    }
}

