package com.example.siriuskoshelok.ui.wallet

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.recycler.adapter.OperationAdapter
import com.example.siriuskoshelok.recycler.decorations.OperationDecoration
import java.util.*
import com.example.siriuskoshelok.ui.operation.AddSumActivity
import com.example.siriuskoshelok.ui.operation.CurrentOperation
import com.example.siriuskoshelok.utils.Constants
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity(R.layout.activity_wallet) {

    companion object {
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

        operationAdapter = OperationAdapter(this)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@WalletActivity).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = operationAdapter
            addItemDecoration(OperationDecoration())
        }

        updateUI()

        btn_add_operation.setOnClickListener {
            CurrentOperation.instance = Operation()
            CurrentOperation.instance?.walletId = wallet.walletId
            val intent = Intent(this, AddSumActivity::class.java)
            this.startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateUI() {
        title_wallet_name.text = wallet.name
        title_money.text = "${wallet.countMoney()} ₽"
        title_money_income.text = "${wallet.countIncome()} ₽"
        title_money_expenses.text = "${wallet.countExpense()} ₽"

        if (wallet.limit != null && wallet.countExpense() > wallet.limit!!) {
            img_limit_error.isVisible = true
            title_money_limit.setTextColor(Color.RED)
        } else {
            img_limit_error.isVisible = false
            title_money_limit.setTextColor(Color.GRAY)
        }

        if (wallet.limit != null)
            title_money_limit.text = "/${wallet.limit}"
        else
            title_money_limit.visibility = View.INVISIBLE


        if (wallet.operationList.isEmpty()) {
            recycler.isVisible = false
            empty_view.isVisible = true
        } else {
            recycler.isVisible = true
            empty_view.isVisible = false
            operationAdapter.setData(wallet.operationList)
        }
        CategoriesDataSet.list.forEach { cat -> cat.isSelected = false }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
