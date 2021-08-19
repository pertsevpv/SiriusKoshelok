package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.OperationsDataSet

class AddTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_type)
        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_type)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val btnAddType: Button = findViewById(R.id.btn_add_type)
        val textIncome: TextView = findViewById(R.id.text_income)
        val textExpenses: TextView = findViewById(R.id.text_expenses)
        val btnIncome: ImageView = findViewById(R.id.btn_income)
        val btnExpenses: ImageView = findViewById(R.id.btn_expenses)

        textIncome.setOnClickListener {
            btnIncome.visibility = View.VISIBLE
            btnExpenses.visibility = View.INVISIBLE
            btnAddType.isEnabled = true
        }
        textExpenses.setOnClickListener {
            btnExpenses.visibility = View.VISIBLE
            btnIncome.visibility = View.INVISIBLE
            btnAddType.isEnabled = true
        }
        val intent =
            Intent(
                this,
                if (!isEdit) AddCategoryActivity::class.java
                else AddOperationActivity::class.java
            )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        btnAddType.setOnClickListener {
            if (btnIncome.visibility == View.VISIBLE) {
                CurrentOp.currentOperation!!.operationType =
                    textIncome.text.toString()
                if (isEdit) finish()
                startActivity(intent)
            }else if (btnExpenses.visibility == View.VISIBLE) {
                CurrentOp.currentOperation!!.operationType =
                    textExpenses.text.toString()
                if (isEdit) finish()
                startActivity(intent)
            }
        }
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