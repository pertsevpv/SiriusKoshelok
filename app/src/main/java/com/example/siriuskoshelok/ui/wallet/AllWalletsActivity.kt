package com.example.siriuskoshelok.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.ui.wallet.presenter.AllWalletsPresenter
import kotlinx.android.synthetic.main.activity_all_wallets.*

class AllWalletsActivity : AppCompatActivity(R.layout.activity_all_wallets) {

    val presenter = AllWalletsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.updateUI()
        presenter.initWalletRecyclerView()
        presenter.initCurrRecyclerView()

        add_wallet_button.setOnClickListener {
            val intent = Intent(this, AddWalletNameActivity::class.java)
            CurrentWallet.start()
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.updateAdapter()
        presenter.getCategories()
        presenter.getWallets()
    }

    private var backPressedQ = 0
    override fun onBackPressed() {
        if (backPressedQ == 1) {
            backPressedQ = 0
            super.onBackPressed()
        } else {
            backPressedQ++
            Toast.makeText(this, resources.getString(R.string.closing_warning), Toast.LENGTH_SHORT).show()
        }
        Handler().postDelayed({
            backPressedQ = 0
        }, 5000)
    }

}
