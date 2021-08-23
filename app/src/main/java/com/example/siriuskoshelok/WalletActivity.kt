package com.example.siriuskoshelok

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.recycler.OperationAdapter
import com.example.siriuskoshelok.recycler.OperationDecoration
import java.util.*
import com.example.siriuskoshelok.ui.operation.AddSumActivity
import com.example.siriuskoshelok.ui.operation.CurrentOp
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

        operationAdapter.setData(wallet.operationList)
        btn_add_operation.setOnClickListener {
            CurrentOp.currentOperation = Operation()
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
