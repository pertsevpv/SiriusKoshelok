package com.example.siriuskoshelok.recycler.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.hoursAndMinutes


class OperationHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val operationTypeIcon: ImageView = root.findViewById(R.id.operation_type_icon)
    private val operationName: TextView = root.findViewById(R.id.operation_name)
    private val operationType: TextView = root.findViewById(R.id.operation_type)
    private val operationMoney: TextView = root.findViewById(R.id.operation_money)
    private val operationDate: TextView = root.findViewById(R.id.operation_date)

    @SuppressLint("SetTextI18n")
    fun bind(op: Operation) {
        operationTypeIcon.setImageResource(op.getCategory()?.pictureId?:R.drawable.dot_green)
        operationName.text = op.getCategory()?.name
        operationType.text = if (op.getCategory()?.type == true) "Доход" else "Расход"
        operationMoney.text = "${op.amount} ₽"
        operationDate.text = op.getDate().hoursAndMinutes()

    }
}

