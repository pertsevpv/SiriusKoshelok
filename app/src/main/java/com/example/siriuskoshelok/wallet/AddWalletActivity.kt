package com.example.siriuskoshelok.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.siriuskoshelok.R
import android.annotation.SuppressLint
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.siriuskoshelok.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet.list
import com.example.siriuskoshelok.ui.operation.AddCategoryActivity
import com.example.siriuskoshelok.ui.operation.AddSumActivity
import com.example.siriuskoshelok.ui.operation.AddTypeActivity
import com.example.siriuskoshelok.ui.operation.CurrentOp

class AddWalletActivity : AppCompatActivity(R.layout.activity_add_wallet) {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_wallet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val textWalletName: TextView = findViewById(R.id.value_wallet_name)
        val textCurrencyType: TextView = findViewById(R.id.value_wallet_currency)
        val textWalletLimit: TextView = findViewById(R.id.value_wallet_limit)
        textWalletName.text = CurrentWallet.currentWallet?.name.toString()

        //textCurrencyType.text = CurrentWallet.currentWallet?...
        textWalletLimit.text =
            if (CurrentWallet.currentWallet?.hasLimit == true)
                CurrentWallet.currentWallet?.limit.toString()
            else
                "Не установлен"

        findViewById<Button>(R.id.btn_create_wallet).setOnClickListener {
            val intent = Intent(this, AllWalletsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            list.add(CurrentWallet.currentWallet!!)
            CurrentWallet.fin()
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_limit).setOnClickListener {
            val intent = Intent(this, EditLimitActivity::class.java)
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_wallet_currency)

        /*findViewById<AppCompatImageView>(R.id.btn_edit_sum).setOnClickListener {
            val intent = Intent(this, AddSumActivity::class.java)
            //intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_category).setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            //intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }
        findViewById<AppCompatImageView>(R.id.btn_edit_type).setOnClickListener {
            val intent = Intent(this, AddTypeActivity::class.java)
            //intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
            this.finish()
        }*/
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