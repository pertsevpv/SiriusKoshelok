package com.example.siriuskoshelok.common

import com.example.siriuskoshelok.recycler.items.BaseListItem

interface OperationListener {

    fun deleteOperation(operation: BaseListItem, position: Int)
    fun updateOperation(operation: BaseListItem, position: Int)
}