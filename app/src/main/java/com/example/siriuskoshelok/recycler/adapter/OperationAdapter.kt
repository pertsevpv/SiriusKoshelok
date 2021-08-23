package com.example.siriuskoshelok.recycler.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.recycler.holder.HeaderHolder
import com.example.siriuskoshelok.recycler.holder.OperationHolder
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.dayMonthYear
import com.example.siriuskoshelok.recycler.items.*
import com.example.siriuskoshelok.ui.operation.AddOperationActivity
import com.example.siriuskoshelok.ui.operation.CurrentOp
import java.lang.Exception
import kotlin.collections.ArrayList

@Suppress("WildcardImport")
class OperationAdapter(private val activity: WalletActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<BaseListItem> = arrayListOf()

    fun setData(list: List<Operation>) {
        data.clear()
        val tmpList = list.sortedBy { it.date.time }
        val operationMap = tmpList.groupBy { it.date.dayMonthYear() }

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
        CurrentOp.isEdit = true
        CurrentOp.currentOperation = (data[holder.adapterPosition] as OperationItem).operation
        CurrentOp.posInDataSet = holder.adapterPosition
        CurrentOp.posInOperationList =
            WalletDataSet.list[WalletActivity.indexWallet].operationList.indexOf(CurrentOp.currentOperation)
        val intent = Intent(activity, AddOperationActivity::class.java)
        activity.startActivity(intent)
    }

    private fun onClickedDelete(holder: OperationHolder) {
        AlertDialog.Builder(activity).apply {
            setTitle("Вы действительно хотите удалить эту запись?")
            setNegativeButton("Отменить") { dialog, _ ->
                dialog.cancel()
            }
            setPositiveButton("Удалить") { dialog, _ ->
                WalletDataSet.list[WalletActivity.indexWallet]
                    .operationList.remove((data[holder.adapterPosition] as OperationItem).operation)
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
                        .operationList.remove((data[holder.adapterPosition] as OperationItem).operation)
                    notifyItemRemoved(holder.adapterPosition)
                }
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
