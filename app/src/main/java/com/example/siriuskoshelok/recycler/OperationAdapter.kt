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
        /*if (data.isEmpty()) {
            data.add(OperationItem(op))
            data.add(HeaderItem(op.date))
            notifyDataSetChanged()
        } else {
            val opDate = op.date.normalize()?.time ?: return
            var curDate = (data.last() as HeaderItem).date.time
            var pos = data.size - 1
            var prev = data.size - 1
            while (opDate < curDate) {
                if (data[pos] is HeaderItem) {
                    curDate = data[pos].getDateLong()
                    if (opDate < curDate) prev = pos
                }
                pos--
            }
            if (opDate == curDate) {
                while (pos >= 0 && data[pos] is OperationItem) {
                    Log.i("time1",data[pos].getDateLong().toString())
                    Log.i("time2",op.date.time.toString())
                    if (data[pos].getDateLong() <= op.date.time) {
                        data.add(pos, OperationItem(op))
                        notifyDataSetChanged()
                        return
                    }
                    pos--
                }
                //data.add(pos,OperationItem(op))
            } else {
                data.add(prev, OperationItem(op))
                data.add(prev, HeaderItem(Date(opDate)))
            }
            notifyDataSetChanged()
        }*/
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