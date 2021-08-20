package com.example.siriuskoshelok.wallet.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.wallet.AddWalletActivity
import com.example.siriuskoshelok.wallet.CurrentWallet

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
                CurrentWallet.entity = data[adapterPosition].copy()
                CurrentWallet.isEdit = true
                CurrentWallet.posInDataSet = adapterPosition
                CurrentWallet.posInOperationList =
                    WalletDataSet.list.indexOf(CurrentWallet.entity)
                val intent = Intent(activity, AddWalletActivity::class.java)
                activity.startActivity(intent)
            }
            itemView.findViewById<ImageView>(R.id.del_wal_img).setOnClickListener {
                WalletDataSet.list.remove(data[adapterPosition])
                data.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
            itemView.findViewById<ImageView>(R.id.eye_wal_img).setOnClickListener {
            }
            itemView.findViewById<ConstraintLayout>(R.id.wallet_item_layout).setOnClickListener {
                val intent = Intent(activity, WalletActivity::class.java)
                intent.putExtra("WALLET_KEY", WalletDataSet.list.indexOf(data[adapterPosition]))
                activity.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wal = data[position]
        (holder as WalletHolder).bind(wal)
    }

    override fun getItemCount(): Int = data.size

}
