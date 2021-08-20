package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.CategoryAdapter

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: CategoryAdapter

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.rv_category)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_category)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val btnGift: ImageView = findViewById(R.id.btn_gift)
        val btnSalary: ImageView = findViewById(R.id.btn_salary)
        val btnCapitalisation: ImageView = findViewById(R.id.btn_capitalisation)
        val btnPartTime: ImageView = findViewById(R.id.btn_part_time)
        val intent =
            Intent(
                this,
                if (!isEdit) AddOperationActivity::class.java
                else AddOperationActivity::class.java
            )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        btnAddCategory.setOnClickListener {
            when {
                btnSalary.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation?.extendedOperationType =
                        textSalary.text.toString()
                    CurrentOp.currentOperation?.img =
                        R.drawable.ic_salary
                    if (isEdit) finish()
                    startActivity(intent)
                }
                btnPartTime.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation?.extendedOperationType =
                        textPartTime.text.toString()
                    CurrentOp.currentOperation?.img =
                        R.drawable.ic_part_time
                    if (isEdit) finish()
                    startActivity(intent)
                }
                btnGift.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation?.extendedOperationType =
                        textGift.text.toString()
                    CurrentOp.currentOperation?.img =
                        R.drawable.ic_gift
                    if (isEdit) finish()
                    startActivity(intent)
                }
                btnCapitalisation.visibility == View.VISIBLE -> {
                    CurrentOp.currentOperation?.extendedOperationType =
                        textCapitalisation.text.toString()
                    CurrentOp.currentOperation?.img =
                        R.drawable.ic_capitalisation
                    if (isEdit) finish()
                    startActivity(intent)
                }
            }
        }
        val categories = mutableListOf(
            Category(
                R.drawable.ic_salary,
                resources.getString(R.string.title_salary),
                resources.getString(R.string.income),
                false
            ),
            Category(
                R.drawable.ic_salary,
                resources.getString(R.string.title_part_time),
                resources.getString(R.string.income),
                false
            ),
            Category(
                R.drawable.ic_gift,
                resources.getString(R.string.title_gift),
                resources.getString(R.string.income),
                false
            ),
            Category(
                R.drawable.ic_capitalisation,
                resources.getString(R.string.title_capitalisation),
                resources.getString(R.string.income),
                false
            )
        )

        categoryAdapter = CategoryAdapter().apply {
            setHasStableIds(true)
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@AddCategoryActivity)
            adapter = categoryAdapter
        }
        categoryAdapter.setData(categories)

        val btnAddCategory: Button = findViewById(R.id.btn_add_category)
        btnAddCategory.setOnClickListener {
            val intent = Intent(this, AddOperationActivity::class.java)
            this.startActivity(intent)
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