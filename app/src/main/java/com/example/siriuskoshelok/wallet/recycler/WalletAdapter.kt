package com.example.siriuskoshelok.wallet.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Wallet

class WalletAdapter(private val activity: AppCompatActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<Wallet> = arrayListOf()

    fun setData(list: List<Wallet>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        return WalletHolder(inflater.inflate(R.layout.item_wallet, parent, false)).apply {
            itemView.findViewById<ImageView>(R.id.edit_wal_img).setOnClickListener {
            }
            itemView.findViewById<ImageView>(R.id.del_wal_img).setOnClickListener {
            }
            itemView.findViewById<ImageView>(R.id.eye_wal_img).setOnClickListener {
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wal = data[position]
        (holder as WalletHolder).bind(wal)
    }

    override fun getItemCount(): Int = data.size

}
