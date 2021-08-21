package com.example.siriuskoshelok.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.api.currency.CurrencyResult
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Currency
import com.example.siriuskoshelok.wallet.recycler.CurrencyAdapter
import com.example.siriuskoshelok.wallet.recycler.WalletAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllWalletsActivity : AppCompatActivity(R.layout.activity_all_wallets) {

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.wallet_recycler_view)
    }

    private val currencyRecycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.currency_recycler_view)
    }

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var currAdapter: CurrencyAdapter
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        initOperationRecyclerView()
        initCurrRecyclerView()

        addButton = findViewById(R.id.add_wallet_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            CurrentWallet.start()
            startActivity(intent)
        }
    }

    private fun initOperationRecyclerView() {
        walletAdapter = WalletAdapter(this)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@AllWalletsActivity)
            adapter = walletAdapter
            addItemDecoration(WalletDecoration())
        }
        walletAdapter.setData(WalletDataSet.list)
    }

    private fun initCurrRecyclerView() {
        currAdapter = CurrencyAdapter()
        currencyRecycler.apply {
            layoutManager =
                LinearLayoutManager(this@AllWalletsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = currAdapter
        }
        SiriusApplication.instance.currApiService.getCurrencies().enqueue(CurrCallback(currAdapter))
    }

    private class CurrCallback(val adapter: CurrencyAdapter) : Callback<CurrencyResult> {
        override fun onResponse(call: Call<CurrencyResult>, response: Response<CurrencyResult>) {
            val resultList = mutableListOf<Currency>()
            val result = response.body()?.res
            if (result != null) {
                resultList.add(Currency("USD", 1.0 / result.usd))
                resultList.add(Currency("EUR", 1.0 / result.eur))
                resultList.add(Currency("GBP", 1.0 / result.gbp))
                resultList.add(Currency("CNY", 1.0 / result.cny))
                resultList.add(Currency("JPY", 1.0 / result.jpy))
                resultList.add(Currency("KRW", 1.0 / result.krw))
                resultList.add(Currency("CHF", 1.0 / result.chf))
            }
            adapter.setData(resultList)
        }

        override fun onFailure(call: Call<CurrencyResult>, t: Throwable) {
            //Обидно
        }

    }
}
