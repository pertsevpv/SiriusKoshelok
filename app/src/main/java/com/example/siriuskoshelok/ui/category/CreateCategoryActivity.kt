package com.example.siriuskoshelok.ui.category

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.IconAdapter
import com.example.siriuskoshelok.ui.operation.AddCategoryActivity
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_category.*
import kotlin.random.Random

class CreateCategoryActivity : AppCompatActivity(R.layout.activity_create_category) {

    private lateinit var iconAdapter: IconAdapter

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.rv_icon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_new_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        type.text =
            if (CurrentOperation.instanse?.getCategory()?.type == true) "Доход" else "Расход"

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
                createNewCategory()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun createNewCategory() {
        val cat = Category(
            Drawables.iconList[iconAdapter.getPosDraw()].img,
            new_category.text.toString(),
            type.text == getString(R.string.income), Random.nextLong()
        )
        SiriusApplication.instance.appDatabase.getCategoryDao()
            .insertCategory(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("new category inserted: ", cat.toString())
                CategoriesDataSet.list.add(CategoryItem(cat, false))
                val intent = Intent(this, AddCategoryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }, {
                Log.i("failed to create category: ", it.message ?: "")
            })
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