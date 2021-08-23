package com.example.siriuskoshelok.wallet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.Constants
import com.example.siriuskoshelok.R
import kotlinx.android.synthetic.main.activity_add_wallet_name.*

@Suppress("EmptyFunctionBlock")
class AddWalletNameActivity : AppCompatActivity(R.layout.activity_add_wallet_name) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isEdit = intent.getBooleanExtra(Constants.EDIT_FLAG, false)

        setSupportActionBar(toolbar_wallet_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (!CurrentWallet.entity?.name.isNullOrEmpty()) {
            edit_wal_name.text = Editable.Factory.getInstance()
                .newEditable(CurrentWallet.entity?.name.toString())
            btn_add_wallet_name.isEnabled = true
        }

        btn_add_wallet_name.setOnClickListener {
            if (edit_wal_name.text?.isNotEmpty() == true) {
                CurrentWallet.entity?.name = edit_wal_name.text.toString().trim()
                val intent = Intent(this, AddWalletActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                if (isEdit)
                    finish()
                startActivity(intent)
            }
        }
        edit_wal_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_add_wallet_name.isEnabled = s.isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            CurrentWallet.fin()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
