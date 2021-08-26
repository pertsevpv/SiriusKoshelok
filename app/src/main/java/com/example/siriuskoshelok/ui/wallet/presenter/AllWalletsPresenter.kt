package com.example.siriuskoshelok.ui.wallet.presenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.entity.Currency
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.recycler.adapter.CurrencyAdapter
import com.example.siriuskoshelok.recycler.adapter.WalletAdapter
import com.example.siriuskoshelok.recycler.decorations.WalletDecoration
import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_all_wallets.*

class AllWalletsPresenter(private val activity: AllWalletsActivity) {

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        activity.findViewById<RecyclerView>(R.id.wallet_recycler_view)
    }

    private val currencyRecycler by lazy(LazyThreadSafetyMode.NONE) {
        activity.findViewById<RecyclerView>(R.id.currency_recycler_view)
    }

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var currAdapter: CurrencyAdapter

    @SuppressLint("CheckResult", "SetTextI18n")
    fun getWallets() {
        SiriusApplication.instance.userApiService.getAllWallets(CurrentUser.login ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                Log.i(
                    "api:",
                    "getAllWallets - Success: ".plus(
                        res.list.joinToString(
                            ", ",
                            "[",
                            "]"
                        ) { it.toString() })
                )
                activity.value_total_money.text = "${(res.allProfit - res.allConsumption)} $"
                activity.value_total_income.text = "${res.allProfit} $"
                activity.value_total_expense.text = "${res.allConsumption} $"
                WalletDataSet.list.clear()
                WalletDataSet.list.addAll(res.list)
                WalletDataSet.list.forEach { wal ->
                    getWalletsOperations(wal)
                }
            }, {
                Log.i("api:", "getAllWallets - Fail: ".plus(it.message ?: ""))
                getWalletsFromDb()
            })
    }

    @SuppressLint("CheckResult")
    fun getWalletsFromDb() {
        SiriusApplication.instance.appDatabase.getWalletDao().getByUserLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "getWalletsFromDb - Success ${it.joinToString(", ", "[", "]")}")
                WalletDataSet.list.clear()
                WalletDataSet.list.addAll(it)
                WalletDataSet.list.forEach { wal ->
                    getWalletsOperationsFromDb(wal)
                }
            }, {
                Log.i("database: ", "getWalletsFromDb - Fail $it")
            })
    }

    @SuppressLint("CheckResult")
    fun getWalletsOperations(wal: Wallet) {
        SiriusApplication.instance.walletApiService.getOperations(wal.walletId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                Log.i("api:", "getOperations - Success: ".plus(res.joinToString(", ", "[", "]")))
                wal.operationList.clear()
                wal.operationList.addAll(res)
                updateUI()
                walletAdapter.setData(WalletDataSet.list)
            }, {
                Log.i("api:", "getOperations - Fail: ".plus(it.message ?: ""))
            })
    }

    @SuppressLint("CheckResult")
    fun getWalletsOperationsFromDb(wal: Wallet) {
        SiriusApplication.instance.appDatabase.getOperationDao().getByWalletId(wal.walletId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                wal.operationList.clear()
                wal.operationList.addAll(it)
                updateUI()
                walletAdapter.setData(WalletDataSet.list)
            }, {})
    }

    @SuppressLint("CheckResult")
    fun getCategories() {
        SiriusApplication.instance.categoryApiService.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "getCategories - Success: ${it.joinToString(", ", "[", "]")}")
                if (it.isEmpty()) {
                    CategoriesDataSet.baseCategories.forEach { item ->
                        insertDefault(item.category)
                    }
                } else {
                    CategoriesDataSet.list.clear()
                    CategoriesDataSet.list.addAll(it.map { cat -> CategoryItem(cat, false) })
                }
            }, {
                Log.i("api: ", "getCategories - Fail: $it")
                getCategoriesFromDb()
            })
    }

    @SuppressLint("CheckResult")
    private fun insertDefault(cat: Category) {
        Log.i("ins:", cat.toString())
        SiriusApplication.instance.categoryApiService.createCategory(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("api: ", "insertDefault - Success: $it")
                insertDefaultToDb(it)
            }, {
                Log.i("api: ", "insertDefault - Fail: $it")
            })
    }


    @SuppressLint("CheckResult")
    private fun insertDefaultToDb(cat: Category) {
        SiriusApplication.instance.appDatabase.getCategoryDao().insertCategory(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "insertDefault - Success: $cat")
                CategoriesDataSet.list.addAll(CategoriesDataSet.baseCategories)
            }, {
                Log.i("database: ", "insertDefault - Fail: $it")
            })
    }

    @SuppressLint("CheckResult")
    fun getCategoriesFromDb() {
        SiriusApplication.instance.appDatabase.getCategoryDao().getByUserLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "getCategories - Success: ${it.joinToString(", ", "[", "]")}")

                CategoriesDataSet.list.clear()
                CategoriesDataSet.list.addAll(it.map { el -> CategoryItem(el, false) })

            }, {
                Log.i("error get categories", it.message ?: "")
            })
    }

    fun initWalletRecyclerView() {
        walletAdapter = WalletAdapter(activity)
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = walletAdapter
            addItemDecoration(WalletDecoration())
        }
    }

    @SuppressLint("CheckResult")
    fun initCurrRecyclerView() {
        currAdapter = CurrencyAdapter()
        currencyRecycler.apply {
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = currAdapter
        }

        SiriusApplication.instance.currApiService.getCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val resultList = mutableListOf<Currency>()
                val result = it.res
                resultList.add(Currency("USD", 1.0 / result.usd))
                resultList.add(Currency("EUR", 1.0 / result.eur))
                resultList.add(Currency("GBP", 1.0 / result.gbp))
                resultList.add(Currency("CNY", 1.0 / result.cny))
                resultList.add(Currency("JPY", 1.0 / result.jpy))
                resultList.add(Currency("KRW", 1.0 / result.krw))
                resultList.add(Currency("CHF", 1.0 / result.chf))
                currAdapter.setData(resultList)
            }, {/*Обидно*/ })

    }

    fun updateAdapter() {
        walletAdapter.notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    fun updateUI() {
        activity.value_total_money.text = "${WalletDataSet.countMoney()} $"
        activity.value_total_expense.text = "${WalletDataSet.countExpense()} $"
        activity.value_total_income.text = "${WalletDataSet.countIncome()} $"
    }
}