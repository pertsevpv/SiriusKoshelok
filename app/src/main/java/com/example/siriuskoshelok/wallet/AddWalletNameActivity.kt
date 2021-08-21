package com.example.siriuskoshelok.wallet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.R

@Suppress("EmptyFunctionBlock")
class AddWalletNameActivity : AppCompatActivity(R.layout.activity_add_wallet_name) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_wallet_name)
        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val editWalletName: EditText = findViewById(R.id.edit_wal_name)
        val btnAddWalletName: Button = findViewById(R.id.btn_add_wallet_name)
        editWalletName.setText(CurrentWallet.entity?.name ?: "")

        if (CurrentWallet.entity?.name.isNullOrEmpty()) {
            editWalletName.text = Editable.Factory.getInstance()
                .newEditable(CurrentWallet.entity?.name.toString())
            btnAddWalletName.isEnabled = true
        }

        btnAddWalletName.setOnClickListener {
            if (editWalletName.text.isNotEmpty()) {
                CurrentWallet.entity?.name = editWalletName.text.toString().trim()
                val intent = Intent(this, AddWalletActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                if (isEdit) finish()
                startActivity(intent)
            }
        }
        editWalletName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btnAddWalletName.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
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
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
