package com.example.siriuskoshelok.ui.operation

import com.example.siriuskoshelok.entity.Operation

object CurrentOp {

    var currentOperation: Operation? = null

    var isEdit = false
    var posInDataSet = -1
    var posInOperationList = -1

    fun fin(){
        isEdit = false
        posInDataSet = -1
        posInOperationList = -1
    }
}
