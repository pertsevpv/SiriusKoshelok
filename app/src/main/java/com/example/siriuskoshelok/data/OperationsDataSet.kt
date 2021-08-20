package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Operation
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Suppress("MagicNumber")
object OperationsDataSet {

    val list: MutableList<Operation> = mutableListOf(
        Operation(R.drawable.ic_part_time, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 7, 20, 12, 24)),
        Operation(R.drawable.ic_part_time, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 7, 20, 13, 24)),
        Operation(R.drawable.ic_part_time, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 7, 20, 14, 24)),

        Operation(R.drawable.ic_capitalisation, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 6, 20, 12, 24)),
        Operation(R.drawable.ic_capitalisation, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 6, 20, 13, 24)),
        Operation(R.drawable.ic_capitalisation, 1, "Доход", "Тип Операции", GregorianCalendar(2021, 6, 20, 14, 24)),
        )
}
