package com.example.siriuskoshelok.entity

import java.util.*

// TODO: 18.08.2021 redo operation class
data class Operation(
    val money: Double,
    val operationType: OPERATION_TYPE,
    val extendedOperationType: EXTENDED_OPERATION_TYPE,
    val date: Date = Date()
)