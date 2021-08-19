package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            list.add(CurrentOp.currentOperation!!)
            //finish()
            startActivity(intent)
        }
        findViewById<AppCompatImageView>(R.id.btn_edit_sum).setOnClickListener {
            val intent = Intent(this, AddSumActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }
        findViewById<AppCompatImageView>(R.id.btn_edit_category).setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }
        findViewById<AppCompatImageView>(R.id.btn_edit_type).setOnClickListener {
            val intent = Intent(this, AddTypeActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
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