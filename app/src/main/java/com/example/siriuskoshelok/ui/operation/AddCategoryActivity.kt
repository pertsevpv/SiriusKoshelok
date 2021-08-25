package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet
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

        setSupportActionBar(toolbar_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        categoryAdapter = CategoryAdapter()

        recycler.apply {
            layoutManager = LinearLayoutManager(this@AddCategoryActivity)
            adapter = categoryAdapter
        }

        btn_create_category.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

        btn_add_category.setOnClickListener {
            if (CurrentOperation.instanse?.categoryId != null) {
                val intent =
                    Intent(
                        this,
                        AddOperationActivity::class.java
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                this.startActivity(intent)
            }
        }

        updateUI()
    }

    private fun updateUI() {
        categoryAdapter.setData(CategoriesDataSet.list.filter { it.category.type == CurrentOperation.category?.type })
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