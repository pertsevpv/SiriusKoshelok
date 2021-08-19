package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.OperationsDataSet

class AddCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_category)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val btnAddCategory: Button = findViewById(R.id.btn_add_category)
        val textSalary: TextView = findViewById(R.id.text_salary)
        val textPartTime: TextView = findViewById(R.id.text_part_time)
        val textCapitalisation: TextView = findViewById(R.id.text_capitalisation)
        val textGift: TextView = findViewById(R.id.text_gift)

        val btnGift: ImageView = findViewById(R.id.btn_gift)
        val btnSalary: ImageView = findViewById(R.id.btn_salary)
        val btnCapitalisation: ImageView = findViewById(R.id.btn_capitalisation)
        val btnPartTime: ImageView = findViewById(R.id.btn_part_time)
        val intent = Intent(this, AddOperationActivity::class.java)
        btnAddCategory.setOnClickListener {
            when {
                btnSalary.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation!!.extendedOperationType =
                        textSalary.text.toString()
                    CurrentOp.currentOperation!!.img =
                        R.drawable.ic_salary
                    this.startActivity(intent)
                }
                btnPartTime.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation!!.extendedOperationType =
                        textPartTime.text.toString()
                    CurrentOp.currentOperation!!.img =
                        R.drawable.ic_part_time
                    this.startActivity(intent)
                }
                btnGift.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation!!.extendedOperationType =
                        textGift.text.toString()
                    CurrentOp.currentOperation!!.img =
                        R.drawable.ic_gift
                    this.startActivity(intent)
                }
                btnCapitalisation.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation!!.extendedOperationType =
                        textCapitalisation.text.toString()
                    CurrentOp.currentOperation!!.img =
                        R.drawable.ic_capitalisation
                    this.startActivity(intent)
                }
            }
        }

        textSalary.setOnClickListener {
            btnSalary.visibility = View.VISIBLE
            btnGift.visibility = View.INVISIBLE
            btnPartTime.visibility = View.INVISIBLE
            btnCapitalisation.visibility = View.INVISIBLE
            btnAddCategory.isEnabled = true
        }
        textPartTime.setOnClickListener {
            btnSalary.visibility = View.INVISIBLE
            btnGift.visibility = View.INVISIBLE
            btnPartTime.visibility = View.VISIBLE
            btnCapitalisation.visibility = View.INVISIBLE
            btnAddCategory.isEnabled = true
        }
        textCapitalisation.setOnClickListener {
            btnSalary.visibility = View.INVISIBLE
            btnGift.visibility = View.INVISIBLE
            btnPartTime.visibility = View.INVISIBLE
            btnCapitalisation.visibility = View.VISIBLE
            btnAddCategory.isEnabled = true
        }
        textGift.setOnClickListener {
            btnSalary.visibility = View.INVISIBLE
            btnGift.visibility = View.VISIBLE
            btnPartTime.visibility = View.INVISIBLE
            btnCapitalisation.visibility = View.INVISIBLE
            btnAddCategory.isEnabled = true
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