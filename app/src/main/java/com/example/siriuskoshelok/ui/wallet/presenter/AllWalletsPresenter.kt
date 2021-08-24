package com.example.siriuskoshelok.ui.wallet.presenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Currency
import com.example.siriuskoshelok.recycler.adapter.CurrencyAdapter
import com.example.siriuskoshelok.recycler.adapter.WalletAdapter
import com.example.siriuskoshelok.recycler.decorations.WalletDecoration
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

    @SuppressLint("CheckResult")
    fun uploadFromDb() {
        SiriusApplication.instance.appDatabase.getWalletDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                WalletDataSet.list.clear()
                WalletDataSet.list.addAll(it)
                initWalletRecyclerView()
            }, {})
        Log.i("uploaded", WalletDataSet.list.joinToString(", "))
    }

    fun initWalletRecyclerView() {
        walletAdapter = WalletAdapter(activity)
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = walletAdapter
            addItemDecoration(WalletDecoration())
        }
        walletAdapter.setData(WalletDataSet.list)
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

    @SuppressLint("SetTextI18n")
    fun updateUI() {
        activity.value_total_money.text = "${WalletDataSet.countMoney()} $"
        activity.value_total_expense.text = "${WalletDataSet.countExpense()} $"
        activity.value_total_income.text = "${WalletDataSet.countIncome()} $"
    }
}