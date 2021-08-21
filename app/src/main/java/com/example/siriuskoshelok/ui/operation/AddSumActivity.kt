package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.example.siriuskoshelok.R
import kotlinx.android.synthetic.main.activity_add_sum.*

class AddSumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sum)
        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)
        setSupportActionBar(toolbar_sum)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (CurrentOp.currentOperation?.money != null) {
            edit_sum.text = Editable.Factory.getInstance().newEditable(CurrentOp.currentOperation?.money.toString())
            btn_add_sum.isEnabled = true
        }
        btn_add_sum.setOnClickListener {
            if (edit_sum.text!!.isNotEmpty()) {
                CurrentOp.currentOperation?.money = edit_sum.text.toString().toInt()
                val intent =
                    Intent(
                        this,
                        if (!isEdit) AddTypeActivity::class.java
                        else AddOperationActivity::class.java
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                if (isEdit) finish()
                startActivity(intent)
            }
        }
        edit_sum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_add_sum.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {}
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