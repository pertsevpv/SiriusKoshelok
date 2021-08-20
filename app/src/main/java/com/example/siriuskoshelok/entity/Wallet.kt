package com.example.siriuskoshelok.entity

class Wallet(
    var name: String? = "",
    var balance: Int = 0,
    val operationList: List<Operation> = emptyList(),
    var hasLimit: Boolean = false,
    var limit: Int = Int.MAX_VALUE
)