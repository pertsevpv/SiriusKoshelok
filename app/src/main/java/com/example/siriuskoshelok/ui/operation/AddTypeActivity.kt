package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.entity.Category
import kotlinx.android.synthetic.main.activity_add_type.*

class AddTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_type)
        val isEdit = intent.getBooleanExtra(Constants.EDIT_FLAG, false)

        setSupportActionBar(toolbar_type)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        CategoriesDataSet.list.forEach { it.isSelected = false }

        CurrentOperation.category = Category()
        if (CurrentOperation.category?.type != null) {
            btn_add_type.isEnabled = true
            if (CurrentOperation.instanse?.getCategory()?.type == true) {
                btn_income.visibility = View.VISIBLE
            }
            if (CurrentOperation.instanse?.getCategory()?.type == false) {
                btn_expenses.visibility = View.VISIBLE
            }
            btn_add_type.isEnabled = true
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
            Intent(
                this,
                AddCategoryActivity::class.java
            )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        btn_add_type.setOnClickListener {
            if (btn_income.visibility == View.VISIBLE) {
                CurrentOperation.category?.type = true

                if (isEdit) finish()
                startActivity(intent)
            } else if (btn_expenses.visibility == View.VISIBLE) {
                CurrentOperation.category?.type = false

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
