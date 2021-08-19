package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.WalletActivity
import com.example.siriuskoshelok.data.OperationsDataSet.list

class AddOperationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_operation)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_operation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val textCountMoney: TextView = findViewById(R.id.count_money)
        val textTypeOperation: TextView = findViewById(R.id.type)
        val textCategory: TextView = findViewById(R.id.category)
        textCountMoney.text = CurrentOp.currentOperation!!.money.toString()
        textTypeOperation.text = CurrentOp.currentOperation!!.operationType
        textCategory.text = CurrentOp.currentOperation!!.extendedOperationType
        val btnCreateOperation: Button = findViewById(R.id.btn_create_operation)
        btnCreateOperation.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            list.add(CurrentOp.currentOperation!!)
            finish()
            startActivity(intent)
        }
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