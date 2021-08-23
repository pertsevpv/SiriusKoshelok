package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.siriuskoshelok.Constants
import com.example.siriuskoshelok.R
import kotlinx.android.synthetic.main.activity_add_type.*

class AddTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_type)
        val isEdit = intent.getBooleanExtra(Constants.EDIT_FLAG, false)

        setSupportActionBar(toolbar_type)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if(CurrentOp.currentOperation?.operationType != null){
            btn_add_type.isEnabled = true
            if(CurrentOp.currentOperation?.operationType == text_income.text){
                btn_income.visibility = View.VISIBLE
            }
            if(CurrentOp.currentOperation?.operationType == text_expenses.text){
                btn_expenses.visibility = View.VISIBLE
            }
        }
        text_income.setOnClickListener {
            btn_income.visibility = View.VISIBLE
            btn_expenses.visibility = View.INVISIBLE
            btn_add_type.isEnabled = true
        }
        text_expenses.setOnClickListener {
            btn_expenses.visibility = View.VISIBLE
            btn_income.visibility = View.INVISIBLE
            btn_add_type.isEnabled = true
        }
        val intent =
            Intent(this,
                if (!isEdit) AddCategoryActivity::class.java
                else AddOperationActivity::class.java
            )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        btn_add_type.setOnClickListener {
            if (btn_income.visibility == View.VISIBLE) {
                CurrentOp.currentOperation?.operationType =
                    text_income.text.toString()
                if (isEdit) finish()
                startActivity(intent)
            } else if (btn_expenses.visibility == View.VISIBLE) {
                CurrentOp.currentOperation?.operationType =
                    text_expenses.text.toString()
                if (isEdit) finish()
                startActivity(intent)
                CurrentOp.currentOperation?.operationType = text_income.text.toString()
                this.startActivity(intent)
            }
            if (btn_expenses.visibility == View.VISIBLE) {
                CurrentOp.currentOperation?.operationType = text_expenses.text.toString()
                this.startActivity(intent)
            }
        }
        if(CurrentOp.currentOperation?.operationType != null){
            btn_add_type.isEnabled = true
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
