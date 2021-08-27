package com.example.siriuskoshelok.ui.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.siriuskoshelok.R
import android.annotation.SuppressLint
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import com.example.siriuskoshelok.ui.wallet.presenter.AddWalletPresenter
import com.example.siriuskoshelok.utils.Constants
import kotlinx.android.synthetic.main.activity_add_wallet.*

class AddWalletActivity : AppCompatActivity(R.layout.activity_add_wallet) {

    private val presenter = AddWalletPresenter(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_wallet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        findViewById<Button>(R.id.btn_create_wallet).setOnClickListener(presenter.clickCreate)

        findViewById<AppCompatImageView>(R.id.btn_edit_limit).setOnClickListener {
            val intent = Intent(this, EditLimitActivity::class.java)
            startActivity(intent)
        }

        findViewById<AppCompatImageView>(R.id.btn_edit_wallet_name).setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            intent.putExtra(Constants.EDIT_FLAG, true)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        value_wallet_name.text = CurrentWallet.entity?.name.toString()
        value_wallet_limit.text =
            if (CurrentWallet.entity?.limit != null)
                CurrentWallet.entity?.limit.toString()
            else
                resources.getString(R.string.text_no_limit)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
