package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.WalletActivity
import com.example.siriuskoshelok.*
import com.example.siriuskoshelok.data.OperationsDataSet.list
import kotlinx.android.synthetic.main.activity_add_operation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.dayAndMonth
import java.util.*

class AddOperationActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_operation)
        setSupportActionBar(toolbar_operation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        count_money.text = CurrentOp.currentOperation?.money.toString()
        type.text = CurrentOp.currentOperation?.operationType ?: ""
        category.text = CurrentOp.currentOperation?.extendedOperationType ?: ""
        count_money.text = CurrentOp.currentOperation?.money.toString()
        type.text = CurrentOp.currentOperation?.operationType
        category.text = CurrentOp.currentOperation?.extendedOperationType

        val currentDate = GregorianCalendar()
        date.text = currentDate.dayAndMonth()
        CurrentOp.currentOperation?.date = currentDate

        btn_create_operation.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (CurrentOp.isEdit)
                WalletDataSet.list[WalletActivity.indexWallet].operationList[CurrentOp.posInOperationList] =
                    CurrentOp.currentOperation!!
            else
                WalletDataSet.list[WalletActivity.indexWallet].operationList.add(CurrentOp.currentOperation!!)
            startActivity(intent)
        }
        btn_edit_sum.setOnClickListener {
            val intent = Intent(this, AddSumActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }
        btn_edit_category.setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
        }
        btn_edit_type.setOnClickListener {
            val intent = Intent(this, AddTypeActivity::class.java)
            intent.putExtra("EDIT_FLAG", true)
            startActivity(intent)
            this.finish()
        }

        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar.get(Calendar.MONTH)

        val day = calendar[Calendar.DAY_OF_MONTH]
        CurrentOp.currentOperation?.date = GregorianCalendar(year, month, day)
        btnEditDate.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                { _, _, _, dayOfMonth ->
                    textDate.text = "$dayOfMonth $month"
                    CurrentOp.currentOperation?.date = GregorianCalendar(year, month, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
