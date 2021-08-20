package com.example.siriuskoshelok.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.wallet.recycler.WalletAdapter

class AllWalletsActivity : AppCompatActivity(R.layout.activity_all_wallets) {

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.wallet_recycler_view)
    }

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        walletAdapter = WalletAdapter(this)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@AllWalletsActivity)
            adapter = walletAdapter
            addItemDecoration(WalletDecoration())
        }

        walletAdapter.setData(WalletDataSet.list)

        addButton = findViewById(R.id.add_wallet_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            CurrentWallet.start()
            startActivity(intent)
        }

    }
}
