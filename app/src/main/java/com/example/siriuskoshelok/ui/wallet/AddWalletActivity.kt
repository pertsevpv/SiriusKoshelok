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

class AddWalletActivity : AppCompatActivity(R.layout.activity_add_wallet) {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_wallet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val textWalletName: TextView = findViewById(R.id.value_wallet_name)
        val textWalletLimit: TextView = findViewById(R.id.value_wallet_limit)
        textWalletName.text = CurrentWallet.entity?.name.toString()

        textWalletLimit.text =
            if (CurrentWallet.entity?.hasLimit == true)
                CurrentWallet.entity?.limit.toString()
            else
                "Не установлен"

        findViewById<Button>(R.id.btn_create_wallet).setOnClickListener {
            val intent = Intent(this, AllWalletsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (CurrentWallet.isEdit)
                list[CurrentWallet.posInOperationList] = CurrentWallet.entity!!
            else
                list.add(CurrentWallet.entity!!)
            CurrentWallet.fin()
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_limit).setOnClickListener {
            val intent = Intent(this, EditLimitActivity::class.java)
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_wallet_name).setOnClickListener {
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
