package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.Wallet

object WalletDataSet {
    val list: MutableList<Wallet> = mutableListOf()

    fun countMoney() = countIncome() - countExpense()

    fun countIncome() =
        list
            .map { it.countIncome() }
            .fold(0) { a, b -> a + b }

    fun countExpense() =
        list
            .map { it.countExpense() }
            .fold(0) { a, b -> a + b }

}
