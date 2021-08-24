package com.example.siriuskoshelok.ui.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_edit_limit.*

@Suppress("EmptyFunctionBlock")
class EditLimitActivity : AppCompatActivity(R.layout.activity_edit_limit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_limit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (CurrentWallet.entity?.limit != null) {
            limit_switch.isChecked = true
            findViewById<TextInputLayout>(R.id.input_limit).visibility = View.INVISIBLE
            edit_limit.setText(CurrentWallet.entity?.limit.toString())
            btn_add_limit.isEnabled = true
        } else {
            limit_switch.isChecked = false
            findViewById<TextInputLayout>(R.id.input_limit).visibility = View.VISIBLE
            edit_limit.setText("")
            btn_add_limit.isEnabled = false
        }

        limit_switch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    findViewById<TextInputLayout>(R.id.input_limit).visibility = View.INVISIBLE
                    btn_add_limit.isEnabled = true
                }
                else -> {
                    findViewById<TextInputLayout>(R.id.input_limit).visibility = View.VISIBLE
                    edit_limit.setText("")
                    btn_add_limit.isEnabled = false
                }
            }
        }

        if (CurrentWallet.entity?.limit != null) {
            edit_limit.text = Editable.Factory.getInstance()
                .newEditable(CurrentWallet.entity?.limit.toString())
            btn_add_limit.isEnabled = true
        }

        btn_add_limit.setOnClickListener {
            if (edit_limit.text?.isNotEmpty() == true
                && CurrentWallet.entity?.limit != null
            ) {
                CurrentWallet.entity?.limit = edit_limit.text.toString().toInt()
            }
            val intent =
                Intent(this, AddWalletActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        }
        edit_limit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_add_limit.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
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
            CurrentOperation.instanse = null
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
