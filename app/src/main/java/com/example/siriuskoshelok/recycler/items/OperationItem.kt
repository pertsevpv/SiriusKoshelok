package com.example.siriuskoshelok.recycler.items

import com.example.siriuskoshelok.entity.Operation

class OperationItem(var operation: Operation) : BaseListItem() {

    override fun getType(): Int = OPERATION

    override fun toString(): String = "(${operation.amount} ${operation.date})"

    override fun getDateLong(): Long = operation.date.timeInMillis
}