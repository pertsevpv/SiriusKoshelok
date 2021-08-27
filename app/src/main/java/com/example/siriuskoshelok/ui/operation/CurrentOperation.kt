package com.example.siriuskoshelok.ui.operation

import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.entity.Operation

object CurrentOperation {

    var instance: Operation? = null
    var category: Category? = null

    var isEdit = false
    var posInDataSet = -1
    var posInOperationList = -1

    fun fin() {
        instance = null
        category = null
        isEdit = false
        posInDataSet = -1
        posInOperationList = -1
    }

}
