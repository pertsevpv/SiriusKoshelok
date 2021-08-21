package com.example.siriuskoshelok.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.operation.CurrentOp
import com.google.android.material.textfield.TextInputLayout

@Suppress("EmptyFunctionBlock")
class EditLimitActivity : AppCompatActivity(R.layout.activity_edit_limit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_limit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val editLimit: EditText = findViewById(R.id.edit_limit)
        val btnEditLimit: Button = findViewById(R.id.btn_add_limit)
        val limitSwitch: SwitchCompat = findViewById(R.id.limit_switch)

        if (CurrentWallet.entity?.hasLimit == false) {
            limitSwitch.isChecked = true
            findViewById<TextInputLayout>(R.id.input_limit).visibility = View.INVISIBLE
            editLimit.setText(CurrentWallet.entity?.limit.toString())
            btnEditLimit.isEnabled = true
        } else {
            limitSwitch.isChecked = false
            findViewById<TextInputLayout>(R.id.input_limit).visibility = View.VISIBLE
            editLimit.setText("")
            btnEditLimit.isEnabled = false
        }

        limitSwitch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    findViewById<TextInputLayout>(R.id.input_limit).visibility = View.INVISIBLE
                    CurrentWallet.entity?.hasLimit = false
                    btnEditLimit.isEnabled = true
                }
                else -> {
                    findViewById<TextInputLayout>(R.id.input_limit).visibility = View.VISIBLE
                    CurrentWallet.entity?.hasLimit = true
                    editLimit.setText("")
                    btnEditLimit.isEnabled = false
                }
            }
        }

        if (CurrentWallet.entity?.hasLimit == true) {
            editLimit.text = Editable.Factory.getInstance()
                .newEditable(CurrentWallet.entity?.limit.toString())
            btnEditLimit.isEnabled = true
        }

        btnEditLimit.setOnClickListener {
            if (editLimit.text.isNotEmpty()
                && CurrentWallet.entity?.hasLimit == true
            ) {
                CurrentWallet.entity?.limit = editLimit.text.toString().toInt()
            }
            val intent =
                Intent(this, AddWalletActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        }
        editLimit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btnEditLimit.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
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
            CurrentOp.currentOperation = null
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
