package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.EXTENDED_OPERATION_TYPE
import com.example.siriuskoshelok.entity.OPERATION_TYPE
import com.example.siriuskoshelok.entity.Operation
import java.util.*

object OperationsDataSet {

    val list: MutableList<Operation> = mutableListOf(
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(0 + 60 * 60 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(1 * 86400 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(2 * 86400 * 1000)
        ),
        /*Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(3 * 86400 * 1000)
        ),*/
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(4 * 86400 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(5 * 86400 * 1000)
        )
    )

    /*val list: MutableList<Operation> = mutableListOf(
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY, Date(100 * 1000)),
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY, Date(200 * 1000)),
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY, Date(500 * 1000)),
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY, Date(300 * 1000)),
        Operation(100.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.SALARY, Date(400 * 1000)),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(100000 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(200000 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(500000 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(300000 * 1000)
        ),
        Operation(
            100.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.SALARY,
            Date(400000 * 1000)
        ),
*//*        Operation(400.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.GIFT, Date()),
        Operation(400.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.GIFT, Date()),
        Operation(400.0, OPERATION_TYPE.INCOME, EXTENDED_OPERATION_TYPE.GIFT, Date()),*//*
        Operation(
            400.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.GIFT,
            Date(System.currentTimeMillis() - 86400000)
        ),
        Operation(
            400.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.GIFT,
            Date(System.currentTimeMillis() - 86400000)
        ),
        Operation(
            400.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.GIFT,
            Date(System.currentTimeMillis() - 86400000)
        ),
        Operation(
            400.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.GIFT,
            Date(System.currentTimeMillis() - 86400000)
        ),
        Operation(
            400.0,
            OPERATION_TYPE.INCOME,
            EXTENDED_OPERATION_TYPE.GIFT,
            Date(System.currentTimeMillis() - 86400000)
        )

    )*/

    fun addOp(op: Operation) {
        list.add(op)
    }

}
