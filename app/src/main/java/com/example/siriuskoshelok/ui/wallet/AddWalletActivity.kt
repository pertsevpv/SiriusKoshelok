package com.example.siriuskoshelok.ui.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.siriuskoshelok.R
import android.annotation.SuppressLint
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.siriuskoshelok.data.WalletDataSet.list
import kotlinx.android.synthetic.main.activity_add_wallet.*

class AddWalletActivity : AppCompatActivity(R.layout.activity_add_wallet) {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar_wallet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        value_wallet_name.text = CurrentWallet.entity?.name.toString()
        value_wallet_limit.text =
            if (CurrentWallet.entity?.hasLimit == true)
                CurrentWallet.entity?.limit.toString()
            else "Не установлен"

        btn_create_wallet.setOnClickListener {
            val intent = Intent(this, AllWalletsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (CurrentWallet.isEdit)
                list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!
            else
                list.add(CurrentWallet.entity!!)
            CurrentWallet.fin()
            startActivity(intent)
        }
        btn_edit_limit.setOnClickListener {
            val intent = Intent(this, EditLimitActivity::class.java)
            startActivity(intent)
        }
        btn_edit_wallet_name.setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
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
