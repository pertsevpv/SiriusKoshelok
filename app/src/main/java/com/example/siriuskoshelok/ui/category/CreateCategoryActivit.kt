package com.example.siriuskoshelok.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet.listCategory
import com.example.siriuskoshelok.entity.CategoryResponse
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.IconAdapter
import com.example.siriuskoshelok.ui.operation.AddCategoryActivity
import com.example.siriuskoshelok.ui.operation.CurrentOp
import kotlinx.android.synthetic.main.activity_create_category.*

class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var iconAdapter: IconAdapter

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.rv_icon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        setSupportActionBar(toolbar_new_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        type.text = CurrentOp.currentOperation?.operationType

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
        iconAdapter = IconAdapter().apply {
            setHasStableIds(true)
        }
        recycler.apply {
            layoutManager = GridLayoutManager(this@CreateCategoryActivity, 6)
            adapter = iconAdapter
        }
        iconAdapter.setData(Drawables.iconList)
        btn_create.setOnClickListener {
            if (iconAdapter.getPosDraw() != -1) {
                listCategory.add(
                    CategoryResponse(
                        Category(
                            Drawables.iconList[iconAdapter.getPosDraw()].img,
                            new_category.text.toString(),
                            type.text.toString()
                        ),
                        false
                    )
                )
                val intent = Intent(this, AddCategoryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
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