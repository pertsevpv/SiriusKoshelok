package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Operation
import java.util.*

object OperationsDataSet {

    val list: MutableList<Operation> = mutableListOf(
        Operation(R.drawable.ic_part_time, 1, "1", "1"),
        Operation(R.drawable.ic_part_time, 2, "2", "2"),
        Operation(R.drawable.ic_part_time, 3, "3", "3"),
        Operation(
            R.drawable.ic_part_time,
            4,
            "4",
            "4",
            Date(System.currentTimeMillis() - 86400 * 1000)
        ),
        Operation(
            R.drawable.ic_part_time, 5, "5", "5",
            Date(System.currentTimeMillis() - 86400 * 1000)
        ),
        Operation(
            R.drawable.ic_part_time, 6, "6", "6",
            Date(System.currentTimeMillis() - 86400 * 1000)
        ),
        Operation(
            R.drawable.ic_part_time, 7, "7", "7",
            Date(System.currentTimeMillis() - 86400 * 1000)
        ),
    )

}
