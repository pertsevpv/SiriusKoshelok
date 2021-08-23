package com.example.siriuskoshelok.ui.wallet

import com.example.siriuskoshelok.entity.Wallet

object CurrentWallet {

    var entity: Wallet? = null
    var isEdit = false
    var posInDataSet = -1
    var posInOperationList = -1

    fun start() {
        entity = Wallet()
        isEdit = false
    }

    fun fin() {
        entity = null
        isEdit = false
        posInDataSet = -1
        posInOperationList = -1
    }
}
