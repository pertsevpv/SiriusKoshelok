package com.example.siriuskoshelok.wallet

import com.example.siriuskoshelok.entity.Wallet

object CurrentWallet {

    var currentWallet: Wallet? = null
    var isEdit = false
    var posInDataSet = -1
    var posInOperationList = -1

    fun start() {
        currentWallet = Wallet()
        isEdit = false
    }

    fun fin() {
        currentWallet = null
        isEdit = false
        posInDataSet = -1
        posInOperationList = -1
    }
}
