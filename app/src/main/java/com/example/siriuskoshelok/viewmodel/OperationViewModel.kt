package com.example.siriuskoshelok.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siriuskoshelok.data.OperationRepositoryImpl
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet

class OperationViewModel(
) : ViewModel() {

    private val repository = OperationRepositoryImpl()
    private val _operationsLiveData: MutableLiveData<List<Operation>> = MutableLiveData()
    val operationsLiveData: LiveData<List<Operation>>
        get() = _operationsLiveData

    private var indexWalletList = 0

    fun loadOperations(walletPosition: Int) {
        indexWalletList = walletPosition
        _operationsLiveData.postValue(repository.getOperations(indexWalletList))
    }
}