package com.example.siriuskoshelok.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
        //todo указывать строку и картинку из ресурса в зависимости от типа ошибки (сервер, инет)
//        showMessageError(resources.getString(R.string.text_server_error), R.drawable.ic_error)
//        showMessageError(resources.getString(R.string.text_internet_error), R.drawable.ic_no_internet)
    }

    private fun showMessageError(message: String, imageId: Int){
        val layout: View = this.layoutInflater.inflate(R.layout.layout_error, null)
        val image: ImageView = layout.findViewById<View>(R.id.img_error) as ImageView
        image.setImageResource(imageId)
        val text = layout.findViewById<View>(R.id.text_error) as TextView
        text.text = message
        text.width = 900
        Toast(this).apply {
            duration = Toast.LENGTH_LONG
            setView(layout)
            setGravity(Gravity.TOP, 0, 0)
            show()
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
