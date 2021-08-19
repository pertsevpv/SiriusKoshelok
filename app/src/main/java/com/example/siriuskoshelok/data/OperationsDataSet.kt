package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.Operation
import java.util.*

object OperationsDataSet {

    val list: MutableList<Operation> = mutableListOf()

    fun addOp(op: Operation) {
        list.add(op)
    }

}
