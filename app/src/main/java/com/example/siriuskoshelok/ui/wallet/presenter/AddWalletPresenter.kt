package com.example.siriuskoshelok.ui.wallet.presenter

import android.content.Intent
import android.view.View
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.ui.wallet.AddWalletActivity
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.ui.wallet.CurrentWallet
import kotlin.random.Random

class AddWalletPresenter(private val activity: AddWalletActivity) {

    val clickCreate = View.OnClickListener {
        val intent = Intent(activity, AllWalletsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        if (CurrentWallet.isEdit) {

            SiriusApplication.instance.appDatabase.getWalletDao()
                .updateWallet(CurrentWallet.entity!!)
            WalletDataSet.list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!
        } else {
            CurrentWallet.entity?.walletId = Random.nextLong()
            SiriusApplication.instance.appDatabase.getWalletDao()
                .insertWallet(CurrentWallet.entity!!)
            WalletDataSet.list.add(CurrentWallet.entity!!)
        }

        CurrentWallet.fin()
        activity.startActivity(intent)
    }

}