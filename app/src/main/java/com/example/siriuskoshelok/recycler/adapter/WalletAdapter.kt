package com.example.siriuskoshelok.recycler.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.common.WalletListener
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.recycler.holder.CategoryHolder
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.ui.wallet.CurrentWallet
import com.example.siriuskoshelok.recycler.holder.WalletHolder
import kotlinx.android.synthetic.main.item_wallet.view.*
import kotlinx.android.synthetic.main.operation_swipe_item.view.*
import kotlinx.android.synthetic.main.operation_swipe_item.view.del_wal_img
import kotlinx.android.synthetic.main.operation_swipe_item.view.edit_wal_img

class WalletAdapter(
    private var listener: WalletListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<Wallet> = arrayListOf()

    fun setData(list: List<Wallet>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
        val holder = WalletHolder(rootView)
        holder.itemView.del_wal_img.setOnClickListener {
            listener.deleteWallet(data[holder.adapterPosition], holder.adapterPosition)
        }
        holder.itemView.edit_wal_img.setOnClickListener {
            listener.updateWallet(data[holder.adapterPosition], holder.adapterPosition)
        }
        holder.itemView.wallet_item_layout.setOnClickListener {
            listener.showWallet(data[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wal = data[position]
        (holder as WalletHolder).bind(wal)
    }
    override fun getItemCount(): Int = data.size

}
