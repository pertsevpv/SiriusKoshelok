package com.example.siriuskoshelok.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siriuskoshelok.data.WalletRepositoryImpl
import com.example.siriuskoshelok.entity.Wallet

class WalletViewModel(
) : ViewModel() {

    private val _walletsLiveData: MutableLiveData<List<Wallet>> = MutableLiveData()
    val walletsLiveData: LiveData<List<Wallet>>
        get() = _walletsLiveData

    private val repository = WalletRepositoryImpl()

    fun loadWallets() {
        _walletsLiveData.postValue(repository.getWallets())
    }

    fun addWallet(wallet: Wallet){
        repository.addWallet(wallet)
    }

    fun updateWallet(wallet: Wallet, position: Int){
        repository.updateWallet(wallet, position)
    }

    fun removeWallet(position: Int){
        repository.removeWallet(position)
    }

    fun loadCurrencies(){
        //todo загрузка валют
    }
}