package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet

interface OperationRepository {
    fun getOperations(walletPosition: Int): List<Operation>
    fun getOperation(position: Int): Operation
    fun removeOperation(position: Int)
    fun addOperation(operation: Operation)
    fun updateOperation(operation: Operation, position: Int)
}

class OperationRepositoryImpl(
) : OperationRepository {

    override fun getOperations(walletPosition: Int): List<Operation> {
        return WalletDataSet.list[walletPosition].operationList
    }

    override fun getOperation(position: Int): Operation {
        TODO("Not yet implemented")
    }

    override fun removeOperation(position: Int) {
        TODO("Not yet implemented")
    }

    override fun addOperation(operation: Operation) {
        TODO("Not yet implemented")
    }

    override fun updateOperation(operation: Operation, position: Int) {
        TODO("Not yet implemented")
    }

}