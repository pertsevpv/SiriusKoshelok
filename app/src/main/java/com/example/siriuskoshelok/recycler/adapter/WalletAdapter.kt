package com.example.siriuskoshelok.recycler.adapter

import android.content.Intent
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.ui.wallet.CurrentWallet
import com.example.siriuskoshelok.recycler.holder.WalletHolder
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WalletAdapter(private val activity: AllWalletsActivity) :
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
                onClickedEdit(this)
            }
            itemView.findViewById<ImageView>(R.id.del_wal_img).setOnClickListener {
                onClickedDelete(this)
            }
            itemView.findViewById<ImageView>(R.id.eye_wal_img).setOnClickListener {
            }
            itemView.findViewById<ConstraintLayout>(R.id.wallet_item_layout).setOnClickListener {
                onClickedWallet(this)
            }
        }
    }

    private fun onClickedEdit(holder: WalletHolder) {
        CurrentWallet.entity = data[holder.adapterPosition].copy()
        CurrentWallet.isEdit = true
        CurrentWallet.posInDataSet = holder.adapterPosition
        CurrentWallet.posInOperationList =
            WalletDataSet.list.indexOf(CurrentWallet.entity)
        val intent = Intent(activity, AddWalletActivity::class.java)
        activity.startActivity(intent)
    }

    private fun onClickedDelete(holder: WalletHolder) {
        AlertDialog.Builder(activity).apply {
            setTitle("Вы действительно хотите удалить эту запись?")
            setNegativeButton("Отменить") { dialog, _ ->
                dialog.cancel()
            }
            setPositiveButton("Удалить") { dialog, _ ->
                val rec = data[holder.adapterPosition].copy()
                SiriusApplication.instance.appDatabase.getWalletDao()
                    .deleteWallet(rec)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        WalletDataSet.list.remove(rec)
                        data.removeAt(holder.adapterPosition)
                        notifyItemRemoved(holder.adapterPosition)
                        activity.presenter.updateUI()
                        Log.i("deleted from database: ", rec.toString())
                    }, {
                        Log.i("failed to delete from database: ", it.message ?: "")
                    })
                dialog.cancel()
                setCancelable(true)
            }.show()
        }
    }

    private fun onClickedWallet(holder: WalletHolder) {
        val intent = Intent(activity, WalletActivity::class.java)
        intent.putExtra(
            Constants.WALLET_KEY,
            WalletDataSet.list.indexOf(data[holder.adapterPosition])
        )
        activity.startActivity(intent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wal = data[position]
        (holder as WalletHolder).bind(wal)
    }

    override fun getItemCount(): Int = data.size

}
