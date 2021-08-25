package com.example.siriuskoshelok.ui.wallet.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.ui.wallet.CurrentWallet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.absoluteValue
import kotlin.random.Random

class AddWalletPresenter(private val activity: AddWalletActivity) {

    @SuppressLint("CheckResult")
    val clickCreate = View.OnClickListener {
        val intent = Intent(activity, AllWalletsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        Log.i(
            "CurWallet",
            "${CurrentWallet.entity?.toString()}, ${CurrentWallet.posInDataSet}, ${CurrentWallet.posInOperationList}"
        )
        if (CurrentWallet.isEdit) {
            SiriusApplication.instance.appDatabase.getWalletDao()
                .updateWallet(CurrentWallet.entity!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("updated in database: ", CurrentWallet.entity.toString())
                    WalletDataSet.list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!

                    CurrentWallet.fin()
                }, {
                    CurrentWallet.fin()
                })
        } else {
            CurrentWallet.entity?.walletId = Random.nextLong().absoluteValue
            SiriusApplication.instance.appDatabase.getWalletDao()
                .insertWallet(CurrentWallet.entity!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("inserted into database: ", CurrentWallet.entity.toString())
                    WalletDataSet.list.add(CurrentWallet.entity!!)

                    CurrentWallet.fin()
                }, {
                    CurrentWallet.fin()
                })
        }
        activity.startActivity(intent)
    }

}