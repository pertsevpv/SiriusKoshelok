package com.example.siriuskoshelok.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import kotlinx.android.synthetic.main.activity_create_category.*

class CreateCategoryActivity : AppCompatActivity(R.layout.activity_create_category) {

    private val presenter = CreateCategoryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_new_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        type.text =
            if (CurrentOperation.category?.type == true)
                "Доход"
            else
                getString(R.string.title_expenses)

        val activityLauncher =
            registerForActivityResult(AddNameActivityContract()) { result: String? ->
                if (result != null) {
                    new_category.text = result.toString()
                } else {
                    new_category.text = resources.getString(R.string.new_category)
                }

            }
        btn_name_category.setOnClickListener {
            activityLauncher.launch(1)
        }

        btn_create.setOnClickListener(presenter.onClickedCreateCategory())

        presenter.initIconRecycler()
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
