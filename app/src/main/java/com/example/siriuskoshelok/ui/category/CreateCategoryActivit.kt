package com.example.siriuskoshelok.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.operation.CurrentOp
import kotlinx.android.synthetic.main.activity_create_category.*

class CreateCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        setSupportActionBar(toolbar_new_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val arguments = intent.extras
        type.text = arguments?.get("TYPE_CATEGORY").toString()

        val activityLauncher =
            registerForActivityResult(AddNameActivityContract()) { result: String? ->
                new_category.text = result.toString()
            }
        btn_name_category.setOnClickListener {
            activityLauncher.launch(1)
        }
        CurrentOp.currentOperation?.img = R.drawable.ic_salary
        btn_create.setOnClickListener {
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