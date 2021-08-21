package com.example.siriuskoshelok.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet.listCategory
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.ui.operation.CurrentOp

class CreateCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_new_category)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val textName: TextView = findViewById(R.id.new_category)
        val textType: TextView = findViewById(R.id.type)
        val btnCreateCategory: Button = findViewById(R.id.btn_create)
        val btnAddName: ImageView = findViewById(R.id.btn_name_category)

        val arguments = getIntent().getExtras()
        textType.text = arguments?.get("TYPE_CATEGORY").toString()

        btnAddName.setOnClickListener {
            val intent = Intent(this, AddNameActivity::class.java)
            this.startActivity(intent)
        }
        CurrentOp.currentOperation?.img = R.drawable.ic_salary
        btnCreateCategory.setOnClickListener {
            this.finish()
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