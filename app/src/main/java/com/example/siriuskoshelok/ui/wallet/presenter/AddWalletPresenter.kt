package com.example.siriuskoshelok.ui.wallet.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.ui.wallet.CurrentWallet
import com.example.siriuskoshelok.utils.ErrorUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.absoluteValue
import kotlin.random.Random

@SuppressLint("CheckResult")
class AddWalletPresenter(private val activity: AddWalletActivity) {

    val clickCreate = View.OnClickListener {
        if (CurrentWallet.isEdit) {
            editWallet()
        } else {
            CurrentWallet.entity?.login = CurrentUser.login
            createWallet()
        }
    }

    private fun createWallet() {
        SiriusApplication.instance.walletApiService
            .createNewWallet(CurrentWallet.entity!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                Log.i("api: ", "createWallet - Success: $res")
                CurrentWallet.entity?.walletId = res.walletId
                createWalletDb()
            }, {
                Log.i("api: ", "createWallet - Fail: $it")
                ErrorUtils.showMessage(it, activity)
            })
    }

    private fun createWalletDb() {
        SiriusApplication.instance.appDatabase.getWalletDao()
            .insertWallet(CurrentWallet.entity!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "createWallet - Success: ${CurrentWallet.entity}")
                WalletDataSet.list.add(CurrentWallet.entity!!)
                CurrentWallet.fin()
                toNextActivity()
            }, {
                Log.i("database: ", "createWallet - Fail: $it")
            })
    }

    private fun editWallet() {
        SiriusApplication.instance.walletApiService.editWallet(CurrentWallet.entity!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "editWallet - Success ${CurrentWallet.entity}")
                WalletDataSet.list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!
                editWalletDb()
            }, {
                Log.i("api: ", "editWallet - Fail $it")
                ErrorUtils.showMessage(it, activity)
            })
    }

    private fun editWalletDb() {
        SiriusApplication.instance.appDatabase.getWalletDao()
            .updateWallet(CurrentWallet.entity!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "updateWallet - Success ${CurrentWallet.entity}")
                WalletDataSet.list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!
                CurrentWallet.fin()
                toNextActivity()
            }, {
                Log.i("database: ", "updateWallet - Fail $it")
                CurrentWallet.fin()
            })
    }

    private fun toNextActivity() {
        val intent = Intent(activity, AllWalletsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
    }

}