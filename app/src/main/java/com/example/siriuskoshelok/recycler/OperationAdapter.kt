package com.example.siriuskoshelok.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.recycler.holder.HeaderHolder
import com.example.siriuskoshelok.recycler.holder.OperationHolder
import com.example.siriuskoshelok.recycler.items.*
import com.example.siriuskoshelok.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class OperationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<BaseListItem> = arrayListOf()

    fun setData(list: List<Operation>) {
        data.clear()
        val tmpList = list.sortedBy { it.date.time }
        val operationMap = tmpList.groupBy { it.date.dayMonthYear() }

        operationMap.forEach { (date, ops) ->
            ops.forEach {
                data.add(OperationItem(it))
            }
            data.add(HeaderItem(parseDayMonthYear(date) ?: return))
        }
        notifyDataSetChanged()
    }

    fun insertOperation(op: Operation) {
        // FIXME: 19.08.2021 very bad
        val tmpOpList =
            data.filterIsInstance<OperationItem>().map { it.operation } as MutableList<Operation>
        tmpOpList.add(op)
        setData(tmpOpList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> HeaderHolder(inflater.inflate(R.layout.header_item, parent, false))
            OPERATION -> OperationHolder(inflater.inflate(R.layout.operation_swipe_item, parent, false))
            else -> throw Exception("Unsupported viewType")
        }
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