package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.R
import kotlinx.android.synthetic.main.activity_add_sum.*

@Suppress("EmptyFunctionBlock")
class AddSumActivity : AppCompatActivity() {

    var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sum)
        isEdit = intent.getBooleanExtra(Constants.EDIT_FLAG, false)

        setSupportActionBar(toolbar_sum)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (CurrentOperation.instance?.amount != null) {
            edit_sum.text = Editable.Factory.getInstance()
                .newEditable(CurrentOperation.instance?.amount.toString())
            btn_add_sum.isEnabled = true
        }
        btn_add_sum.setOnClickListener {
            if (edit_sum.text!!.isNotEmpty()) {
                if (!edit_sum.text!!.startsWith('0')) {
                    CurrentOperation.instance?.amount = edit_sum.text.toString().toInt()
                    val intent =
                        Intent(
                            this,
                            if (!isEdit) AddTypeActivity::class.java
                            else AddOperationActivity::class.java
                        )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    if (isEdit) finish()
                    startActivity(intent)
                } else {
                    input_sum.error = resources.getString(R.string.text_input_error)
                }
            }
        }
        edit_sum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_add_sum.isEnabled = s.toString().isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) = Unit

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) = Unit
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (!isEdit)
                CurrentOperation.instance = null
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
