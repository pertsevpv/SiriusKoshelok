package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet.listCategory
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.CategoryAdapter
import com.example.siriuskoshelok.ui.category.CreateCategoryActivity

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

        val categories = mutableListOf(
            Category(
                R.drawable.ic_salary,
                resources.getString(R.string.title_salary),
                resources.getString(R.string.title_income),
                false
            ),
            Category(
                R.drawable.ic_salary,
                resources.getString(R.string.title_part_time),
                resources.getString(R.string.title_income),
                false
            ),
            Category(
                R.drawable.ic_gift,
                resources.getString(R.string.title_gift),
                resources.getString(R.string.title_income),
                false
            ),
            Category(
                R.drawable.ic_capitalisation,
                resources.getString(R.string.title_capitalisation),
                resources.getString(R.string.title_income),
                false
            ),
            Category(
                R.drawable.ic_launch,
                resources.getString(R.string.title_launch),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_market,
                resources.getString(R.string.title_market),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_sport,
                resources.getString(R.string.title_sport),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_train,
                resources.getString(R.string.title_train),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_gas_station,
                resources.getString(R.string.title_gas),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_pharmacy,
                resources.getString(R.string.title_medicine),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_house,
                resources.getString(R.string.title_rent),
                resources.getString(R.string.title_expenses),
                false
            ),
            Category(
                R.drawable.ic_travel,
                resources.getString(R.string.title_travel),
                resources.getString(R.string.title_expenses),
                false
            )
        )

        listCategory.addAll(categories)
        categoryAdapter = CategoryAdapter().apply {
            setHasStableIds(true)
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@AddCategoryActivity)
            adapter = categoryAdapter
        }
        if (CurrentOp.currentOperation?.operationType == resources.getString(R.string.title_income)) {
            categoryAdapter.setData(categories.filter {
                it.operationType.startsWith(
                    resources.getString(
                        R.string.title_income
                    )
                )
            })
        }
        if (CurrentOp.currentOperation?.operationType == resources.getString(R.string.title_expenses)) {
            categoryAdapter.setData(categories.filter {
                it.operationType.startsWith(
                    resources.getString(
                        R.string.title_expenses
                    )
                )
            })
        }

        val btnAddCategory: Button = findViewById(R.id.btn_add_category)
        val btnCreateCategory: TextView = findViewById(R.id.btn_create_category)
        btnCreateCategory.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            intent.putExtra("TYPE_CATEGORY", CurrentOp.currentOperation?.operationType)
            startActivity(intent)
        }
        btnAddCategory.setOnClickListener {
            if (CurrentOp.currentOperation?.extendedOperationType != null) {
                val intent =
                    Intent(
                        this,
                        if (!isEdit) AddOperationActivity::class.java
                        else AddOperationActivity::class.java
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                this.startActivity(intent)
            }
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