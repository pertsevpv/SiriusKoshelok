package com.example.siriuskoshelok.recycler.items

import com.example.siriuskoshelok.entity.Operation

class OperationItem(var operation: Operation) : BaseListItem() {

    override fun getType(): Int = OPERATION
}