package com.example.siriuskoshelok.entity

import java.util.*

data class Operation(
    var img: Int? = null,
    var amount: Int? = null,
    var operationType: String? = null,
    var extendedOperationType: String? = null,
    var date: GregorianCalendar = GregorianCalendar()
)
