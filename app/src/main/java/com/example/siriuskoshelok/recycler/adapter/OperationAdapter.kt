package com.example.siriuskoshelok.recycler.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.recycler.holder.HeaderHolder
import com.example.siriuskoshelok.recycler.holder.OperationHolder
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.utils.dayMonthYear
import com.example.siriuskoshelok.recycler.items.*
import com.example.siriuskoshelok.ui.operation.AddOperationActivity
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import kotlin.collections.ArrayList

@Suppress("WildcardImport")
class OperationAdapter(private val activity: WalletActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<BaseListItem> = arrayListOf()

    fun setData(list: List<Operation>) {
        data.clear()
        val tmpList = list.sortedBy { it.getDate().time }
        val operationMap = tmpList.groupBy { it.getDate().dayMonthYear() }

        operationMap.forEach { (date, ops) ->
            ops.forEach {
                data.add(OperationItem(it))
            }
            data.add(HeaderItem(date))
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> HeaderHolder(inflater.inflate(R.layout.header_item, parent, false))
            OPERATION -> OperationHolder(
                inflater.inflate(
                    R.layout.operation_swipe_item,
                    parent,
                    false
                )
            ).apply {
                itemView.findViewById<ImageView>(R.id.edit_wal_img).setOnClickListener {
                    onClickedEdit(this)
                }
                itemView.findViewById<ImageView>(R.id.del_wal_img).setOnClickListener {
                    onClickedDelete(this)
                }
            }
            else -> throw Exception("Unsupported viewType")
        }
    }

    private fun onClickedEdit(holder: OperationHolder) {
        val rec = (data[holder.adapterPosition] as OperationItem).operation
        CurrentOperation.isEdit = true
        CurrentOperation.instanse = rec
        CurrentOperation.category = rec.getCategory()
        CurrentOperation.posInDataSet = holder.adapterPosition
        CurrentOperation.posInOperationList =
            WalletDataSet.list[WalletActivity.indexWallet].operationList.indexOf(CurrentOperation.instanse)
        val intent = Intent(activity, AddOperationActivity::class.java)
        activity.startActivity(intent)
    }

    private fun onClickedDelete(holder: OperationHolder) {
        AlertDialog.Builder(activity).apply {
            setTitle(activity.resources.getString(R.string.text_warning_delete))
            setNegativeButton(activity.resources.getString(R.string.text_negative_button)) { dialog, _ ->
                dialog.cancel()
            }
            setPositiveButton(activity.resources.getString(R.string.text_positive_btn)) { dialog, _ ->
                val rec = data[holder.adapterPosition] as OperationItem
                SiriusApplication.instance.appDatabase.getOperationDao()
                    .deleteOperation(rec.operation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("removed from database: ", rec.operation.toString())
                        WalletDataSet.list[WalletActivity.indexWallet]
                            .operationList.remove(rec.operation)
                        if (data[holder.adapterPosition + 1] is HeaderItem
                            && (holder.adapterPosition == 0 || data[holder.adapterPosition - 1] is HeaderItem)
                        ) {
                            data.removeAt(holder.adapterPosition)
                            data.removeAt(holder.adapterPosition)
                            if (data.isNotEmpty()) notifyItemRangeRemoved(holder.adapterPosition, 2)
                            else notifyDataSetChanged()
                        } else {
                            data.removeAt(holder.adapterPosition)
                            WalletDataSet.list[WalletActivity.indexWallet]
                                .operationList.remove(rec.operation)
                            notifyItemRemoved(holder.adapterPosition)
                        }
                    }, {})

                activity.updateUI()
                dialog.cancel()
            }
            setCancelable(true)
        }.show()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER -> {
                val header = data[position] as HeaderItem
                (holder as HeaderHolder).bind(header.date)
            }
            OPERATION -> {
                val operation = data[position] as OperationItem
                (holder as OperationHolder).bind(operation.operation)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

}
