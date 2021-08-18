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
import com.example.siriuskoshelok.dayAndMonth
import java.lang.Exception

class OperationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: ArrayList<BaseListItem> = arrayListOf()

    fun setData(list: List<Operation>) {
        data.clear()
        val tmpList = list.sortedByDescending { it.date.time }
        Log.i("tmpList", tmpList.map { it.date.dayAndMonth().plus(it.date.dayAndMonth()) }.joinToString(", "))
        val operationMap = tmpList.groupBy { it.date.dayAndMonth() }

        operationMap.forEach { (date, ops) ->
            Log.i("OOO operMap", date)
            data.add(HeaderItem(date))
            ops.forEach {
                data.add(OperationItem(it))
                Log.i("OOO oper", it.toString())
            }
        }
        //notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> HeaderHolder(inflater.inflate(R.layout.header_item, parent, false))
            OPERATION -> OperationHolder(inflater.inflate(R.layout.operation_item, parent, false))
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