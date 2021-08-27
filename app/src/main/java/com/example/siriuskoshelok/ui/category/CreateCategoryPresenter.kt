package com.example.siriuskoshelok.ui.category

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.adapter.IconAdapter
import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.ui.operation.AddCategoryActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_category.*
import kotlin.random.Random

@SuppressLint("CheckResult")
class CreateCategoryPresenter(private val activity: CreateCategoryActivity) {

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        activity.findViewById<RecyclerView>(R.id.rv_icon)
    }
    private lateinit var iconAdapter: IconAdapter


    private fun createNewCategory() {
        val cat = Category(
            Drawables.iconList[iconAdapter.getPosDraw()].img,
            activity.new_category.text.toString(),
            activity.type.text.toString() == activity.getString(R.string.income),
        )
        SiriusApplication.instance.categoryApiService.createCategory(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                Log.i("api: ", "createCategory - Success: $cat")
                CategoriesDataSet.list.add(CategoryItem(res, false))
                insertCategoryToDb(res)
                val intent = Intent(activity, AddCategoryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(intent)
            }, {
                Log.i("api: ", "createCategory - Fail: $it")
            })
    }

    private fun insertCategoryToDb(cat: Category) {
        SiriusApplication.instance.appDatabase.getCategoryDao()
            .insertCategory(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "createCategory - Success: $cat")
            }, {
                Log.i("database: ", "createCategory - Fail: ${it.message}")
            })
    }

    fun initIconRecycler() {
        iconAdapter = IconAdapter().apply {
            setHasStableIds(true)
        }
        recycler.apply {
            layoutManager = GridLayoutManager(activity, 6)
            adapter = iconAdapter
        }
        iconAdapter.setData(Drawables.iconList)
        iconAdapter.reset()
    }

    fun onClickedCreateCategory() = View.OnClickListener {
        if (iconAdapter.getPosDraw() != -1) {
            createNewCategory()
        }
    }

}