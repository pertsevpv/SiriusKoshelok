package com.example.siriuskoshelok.ui.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.siriuskoshelok.R
import android.annotation.SuppressLint
import android.content.Intent
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_add_wallet.*

class AddWalletActivity : AppCompatActivity(R.layout.activity_add_wallet) {

    private lateinit var walletViewModel: WalletViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar_wallet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        walletViewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        walletViewModel.loadWallets()

        value_wallet_name.text = CurrentWallet.entity?.name.toString()
        value_wallet_limit.text =
            if (CurrentWallet.entity?.hasLimit == true)
                CurrentWallet.entity?.limit.toString()
            else resources.getString(R.string.text_no_limit)

        btn_create_wallet.setOnClickListener {
            val intent = Intent(this, AllWalletsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (CurrentWallet.isEdit)
                walletViewModel.updateWallet(CurrentWallet.entity!!, CurrentWallet.posInOperationList)
            else
                walletViewModel.addWallet(CurrentWallet.entity!!)
            CurrentWallet.fin()
            startActivity(intent)
        }
        btn_edit_limit.setOnClickListener {
            val intent = Intent(this, EditLimitActivity::class.java)
            startActivity(intent)
        }
        btn_edit_wallet_name.setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            intent.putExtra(Constants.EDIT_FLAG, true)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
