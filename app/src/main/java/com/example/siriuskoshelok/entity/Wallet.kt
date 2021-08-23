package com.example.siriuskoshelok.entity

data class Wallet(
    var name: String? = "",
    var balance: Int = 0,
    val operationList: MutableList<Operation> = mutableListOf(),
    var hasLimit: Boolean = false,
    var limit: Int = Int.MAX_VALUE
) {

    fun countExpense(): Int =
        operationList
            .filter { it.operationType == "Расход" }
            .map { it.amount ?: 0 }
            .fold(0) { a, b -> a + b }


    fun countIncome(): Int =
        operationList
            .filter { it.operationType == "Доход" }
            .map { it.amount ?: 0 }
            .fold(0) { a, b -> a + b }


    fun countMoney(): Int =
        countIncome() - countExpense()

}
