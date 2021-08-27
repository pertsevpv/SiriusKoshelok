package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.utils.ErrorUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
            .postOperation(CurrentOperation.instance!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "addOperation - Success: $it")
                addOperationDb(it)
                CurrentOperation.fin()
            }, {
                Log.i("api: ", "addOperation - Fail: $it")
                ErrorUtils.showMessage(it, activity)
            })
    }

    private fun addOperationDb(op: Operation) {
        SiriusApplication.instance.appDatabase.getOperationDao()
            .insertOperation(op)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "addOperation - Success: $op")
                WalletActivity.wallet.operationList.add(op)
                toNextActivity()
            }, {
                Log.i("database: ", "addOperation - Fail: $it")

            })
    }

    private fun editOperation() {
        SiriusApplication.instance.operationApiService
            .editOperation(CurrentOperation.instance!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "editOperation - Success: ${CurrentOperation.instance}")
                editOperationDb()
                toNextActivity()
            }, {
                Log.i("api: ", "editOperation - Fail: $it")
                ErrorUtils.showMessage(it, activity)
            })
    }

    private fun editOperationDb() {
        SiriusApplication.instance.appDatabase.getOperationDao()
            .updateOperation(CurrentOperation.instance!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "editOperation - Success: ${CurrentOperation.instance}")
                WalletActivity.wallet
                    .operationList[CurrentOperation.posInOperationList] =
                    CurrentOperation.instance!!
                CurrentOperation.fin()
            }, {
                Log.i("database: ", "editOperation - Fail: $it")
                CurrentOperation.fin()
            })
    }


    private fun toNextActivity() {
        val intent = Intent(activity, WalletActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
    }
}