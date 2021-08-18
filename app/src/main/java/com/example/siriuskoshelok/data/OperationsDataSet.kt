package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.EXTENDED_OPERATION_TYPE
import com.example.siriuskoshelok.entity.OPERATION_TYPE
import com.example.siriuskoshelok.entity.Operation

object OperationsDataSet {

    val list: MutableList<Operation> = mutableListOf(
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY),
        Operation(200.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.CAPITALISATION),
        Operation(400.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.GIFT)
    )

}