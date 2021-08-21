package com.example.siriuskoshelok.ui.operation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet.listCategory
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.CategoryAdapter
import com.example.siriuskoshelok.ui.category.CreateCategoryActivity
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_create_category.*

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: CategoryAdapter

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.rv_category)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val isEdit = intent.getBooleanExtra("EDIT_FLAG", false)
        setSupportActionBar(toolbar_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        categoryAdapter = CategoryAdapter().apply {
            setHasStableIds(true)
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@AddCategoryActivity)
            adapter = categoryAdapter
        }
        if (CurrentOp.currentOperation?.operationType == resources.getString(R.string.title_income)) {
            categoryAdapter.setData(listCategory.filter {
                it.operationType.startsWith(
                    resources.getString(
                        R.string.title_income
                    )
                )
            })
        } else {
            categoryAdapter.setData(listCategory.filter {
                it.operationType.startsWith(
                    resources.getString(
                        R.string.title_expenses
                    )
                )
            })
        }

        btn_create_category.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }
        btn_add_category.setOnClickListener {
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
