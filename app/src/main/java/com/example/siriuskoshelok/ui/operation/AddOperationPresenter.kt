package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.math.absoluteValue

@SuppressLint("CheckResult")
class AddOperationPresenter(private val activity: AddOperationActivity) {

    fun onClickedCreate() {
        if (CurrentOperation.isEdit)
            editOperation()
        else
            addOperation()

    }

    private fun addOperation() {
        SiriusApplication.instance.operationApiService
            .postOperation(CurrentOperation.instanse!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "addOperation - Success: $it")
                addOperationDb(it)
                CurrentOperation.fin()
            }, {
                Log.i("api: ", "addOperation - Fail: $it")
                //CurrentOperation.fin()
            })
    }

    private fun addOperationDb(op: Operation) {
        SiriusApplication.instance.appDatabase.getOperationDao()
            .insertOperation(op)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "addOperation - Success: $op")
                WalletDataSet.list[WalletActivity.indexWallet]
                    .operationList.add(op)
                toNextActivity()
            }, {
                Log.i("database: ", "addOperation - Fail: $it")

            })
    }

    private fun editOperation() {
        SiriusApplication.instance.operationApiService
            .editOperation(CurrentOperation.instanse!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "editOperation - Success: ${CurrentOperation.instanse}")
                editOperationDb()
            }, {
                Log.i("api: ", "editOperation - Fail: $it")
            })
    }

    private fun editOperationDb() {
        SiriusApplication.instance.appDatabase.getOperationDao()
            .updateOperation(CurrentOperation.instanse!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "editOperation - Success: ${CurrentOperation.instanse}")
                WalletDataSet.list[WalletActivity.indexWallet]
                    .operationList[CurrentOperation.posInOperationList] =
                    CurrentOperation.instanse!!
                CurrentOperation.fin()
                toNextActivity()
            }, {
                Log.i("database: ", "editOperation - Fail: $it")
            })
    }

    private fun toNextActivity() {
        val intent = Intent(activity, WalletActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
    }
}