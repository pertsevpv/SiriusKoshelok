package com.example.siriuskoshelok.ui.operation

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.siriuskoshelok.*
import com.example.siriuskoshelok.data.OperationsDataSet.list
import java.util.*

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
        val textDate: TextView = findViewById(R.id.date)
        textCountMoney.text = list.last().money.toString()
        textTypeOperation.text = list.last().operationType
        textCategory.text = list.last().extendedOperationType
        val currentDate = Date()
        textDate.text = currentDate.dayAndMonth()
        list.last().date = currentDate

        val btnCreateOperation: Button = findViewById(R.id.btn_create_operation)
        btnCreateOperation.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        val btnEditSum:ImageView = findViewById(R.id.btn_edit_sum)
        btnEditSum.setOnClickListener {
            val intent = Intent(this, AddSumActivity::class.java)
            this.startActivity(intent)
        }
        val btnEditType:ImageView = findViewById(R.id.btn_edit_type)
        btnEditType.setOnClickListener {
            val intent = Intent(this, AddTypeActivity::class.java)
            this.startActivity(intent)
        }
        val btnEditCategory:ImageView = findViewById(R.id.btn_edit_category)
        btnEditCategory.setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            this.startActivity(intent)
        }
        val btnEditDate:ImageView = findViewById(R.id.btn_edit_date)
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        btnEditDate.setOnClickListener {
            val dpd = DatePickerDialog(this,  { _, _, _, dayOfMonth ->
                textDate.text = "$dayOfMonth $month"
            }, calendar.get(Calendar.YEAR), month, calendar.get(Calendar.DAY_OF_MONTH))
            dpd.show()
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