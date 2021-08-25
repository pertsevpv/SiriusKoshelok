package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.api.currency.CurrencyResult
import com.example.siriuskoshelok.entity.Wallet
import retrofit2.Call

interface WalletRepository {
    fun getWallets(): List<Wallet>
    fun getWallet(position: Int): Wallet
    fun getCurrencies(): Call<CurrencyResult>
    fun removeWallet(position: Int)
    fun addWallet(wallet: Wallet)
    fun updateWallet(wallet: Wallet, position: Int)
}

class WalletRepositoryImpl(
) : WalletRepository {

    override fun getWallets(): List<Wallet> {
        return WalletDataSet.list
    }

    override fun getWallet(position: Int): Wallet {
        return WalletDataSet.list[position]
    }

    override fun getCurrencies(): Call<CurrencyResult> {
        TODO("Not yet implemented")
    }

    override fun removeWallet(position: Int) {
        WalletDataSet.list.removeAt(position)
    }

    override fun addWallet(wallet: Wallet) {
        WalletDataSet.list.add(wallet)
    }

    override fun updateWallet(wallet: Wallet, position: Int) {
        WalletDataSet.list[position] = wallet
    }
}