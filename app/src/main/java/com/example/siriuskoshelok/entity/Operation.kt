package com.example.siriuskoshelok.entity

import java.util.*

class Operation(
    val money: Double,
    val operationType: OPERATION_TYPE,
    val extendedOperationType: EXTENDED_OPERATION_TYPE,
    val date: Date = Date()
)