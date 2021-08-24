package com.example.siriuskoshelok.ui.wallet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.recycler.adapter.OperationAdapter
import com.example.siriuskoshelok.recycler.decorations.OperationDecoration
import java.util.*
import com.example.siriuskoshelok.ui.operation.AddSumActivity
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity(R.layout.activity_wallet) {

    companion object {
        var indexWallet: Int = -1
        lateinit var wallet: Wallet
    }

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.operations_recycler_view)
    }

    private lateinit var operationAdapter: OperationAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_wallet)
        setSupportActionBar(toolbar_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        indexWallet = intent.getIntExtra(Constants.WALLET_KEY, indexWallet)
        wallet = WalletDataSet.list[indexWallet]

        updateUI()

        operationAdapter = OperationAdapter(this)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@WalletActivity).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = operationAdapter
            addItemDecoration(OperationDecoration())
        }

        if (wallet.operationList.isEmpty()) {
            recycler.isVisible = false
            empty_view.isVisible = true
        }

        SiriusApplication.instance.appDatabase.getOperationDao().getByWalletId(wallet.walletId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("got operations: ", it.joinToString(", "))
                wallet.operationList.clear()
                wallet.operationList.addAll(it)
                if (wallet.operationList.isEmpty()) {
                    recycler.isVisible = false
                    empty_view.isVisible = true
                }else{
                    recycler.isVisible = true
                    empty_view.isVisible = false
                }
                operationAdapter.setData(it)
            }, {})

        btn_add_operation.setOnClickListener {
            CurrentOperation.instanse = Operation()
            CurrentOperation.instanse?.walletId = wallet.walletId
            val intent = Intent(this, AddSumActivity::class.java)
            this.startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateUI() {
        title_wallet_name.text = wallet.name
        title_money.text = "${wallet.countMoney()} $"
        title_money_income.text = "${wallet.countIncome()} $"
        title_money_expenses.text = "${wallet.countExpense()} $"
        if (wallet.limit != null)
            title_money_limit.text = "/${wallet.limit}"
        else
            title_money_limit.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
