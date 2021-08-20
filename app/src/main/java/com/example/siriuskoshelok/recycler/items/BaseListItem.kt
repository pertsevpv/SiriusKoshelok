package com.example.siriuskoshelok.recycler.items

const val HEADER = 0
const val OPERATION = 1

sealed class BaseListItem {

    abstract fun getType(): Int

    abstract fun getDateLong(): Long
}
