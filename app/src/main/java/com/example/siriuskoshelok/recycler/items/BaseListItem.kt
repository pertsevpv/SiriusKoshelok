package com.example.siriuskoshelok.recycler.items

val HEADER = 0
val OPERATION = 1

abstract class BaseListItem {

    abstract fun getType(): Int
}