package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.OperationsDataSet
import com.example.siriuskoshelok.data.OperationsDataSet.list
import com.example.siriuskoshelok.entity.Operation

class AddSumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sum)
        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_sum)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val editSum: EditText = findViewById(R.id.edit_sum)
        val btnAddSum: Button = findViewById(R.id.btn_add_sum)
        btnAddSum.setOnClickListener {
            if (editSum.text.isNotEmpty()) {
                CurrentOp.currentOperation?.money = editSum.text.toString().toInt()
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
        editSum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btnAddSum.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
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