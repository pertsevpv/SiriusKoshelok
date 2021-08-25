package com.example.siriuskoshelok.common

import com.example.siriuskoshelok.entity.Wallet

interface WalletListener {

    fun showWallet(wallet: Wallet)
    fun deleteWallet(wallet: Wallet, position: Int)
    fun updateWallet(wallet: Wallet, position: Int)
}