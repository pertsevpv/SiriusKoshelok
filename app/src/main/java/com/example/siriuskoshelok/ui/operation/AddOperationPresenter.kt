package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.util.Log
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.math.absoluteValue

@SuppressLint("CheckResult")
class AddOperationPresenter(private val activity: AddOperationActivity) {

    fun onClickedEdit() {
        SiriusApplication.instance.appDatabase.getOperationDao()
            .updateOperation(CurrentOperation.instanse!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("updated operation: ", CurrentOperation.instanse.toString())
                WalletDataSet.list[WalletActivity.indexWallet].operationList[CurrentOperation.posInOperationList] =
                    CurrentOperation.instanse!!
                CurrentOperation.fin()
            }, {})
    }

    fun onClickedAdd() {
        CurrentOperation.instanse?.id = Random().nextLong().absoluteValue
        SiriusApplication.instance.appDatabase.getOperationDao()
            .insertOperation(CurrentOperation.instanse!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("inserted operation: ", CurrentOperation.instanse.toString())
                WalletDataSet.list[WalletActivity.indexWallet]
                    .operationList.add(CurrentOperation.instanse!!)
                CurrentOperation.fin()
            }, {})
    }
}