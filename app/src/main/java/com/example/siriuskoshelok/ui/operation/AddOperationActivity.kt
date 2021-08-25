package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.*
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.ui.wallet.WalletActivity
import com.example.siriuskoshelok.data.WalletDataSet
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.utils.dayAndMonth
import com.example.siriuskoshelok.utils.hoursAndMinutes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_operation.*
import java.util.*
import kotlin.math.absoluteValue

class AddOperationActivity : AppCompatActivity(R.layout.activity_add_operation) {

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_operation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        count_money.text = CurrentOperation.instanse?.amount.toString()
        type.text = CurrentOperation.instanse?.getCategory()?.typeName() ?: ""
        category.text = CurrentOperation.instanse?.getCategory()?.name ?: ""

        val selectedDate = GregorianCalendar()
        date.text = selectedDate.dayAndMonth()
        time.text = selectedDate.hoursAndMinutes()

        btn_create_operation.setOnClickListener {
            CurrentOperation.instanse?.timeMillis = selectedDate.timeInMillis
            val intent = Intent(this, WalletActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


            if (CurrentOperation.isEdit) {
                SiriusApplication.instance.appDatabase.getOperationDao()
                    .updateOperation(CurrentOperation.instanse!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("updated operation: ", CurrentOperation.instanse.toString())
                        WalletDataSet.list[WalletActivity.indexWallet].operationList[CurrentOperation.posInOperationList] =
                            CurrentOperation.instanse!!
                        CurrentOperation.fin()
                    }, {})
            } else {
                CurrentOperation.instanse?.id = Random().nextLong().absoluteValue
                SiriusApplication.instance.appDatabase.getOperationDao()
                    .insertOperation(CurrentOperation.instanse!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("inserted operation: ", CurrentOperation.instanse.toString())
                        WalletDataSet.list[WalletActivity.indexWallet]
                            .operationList.add(CurrentOperation.instanse!!)
                        CurrentOperation.fin()
                    }, {})
            }

            startActivity(intent)
        }
        btn_edit_sum.setOnClickListener {
            val intent = Intent(this, AddSumActivity::class.java)
            intent.putExtra(Constants.EDIT_FLAG, true)
            startActivity(intent)
        }
        btn_edit_category.setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra(Constants.EDIT_FLAG, true)
            startActivity(intent)
        }
        btn_edit_type.setOnClickListener {
            val intent = Intent(this, AddTypeActivity::class.java)
            intent.putExtra(Constants.EDIT_FLAG, true)
            startActivity(intent)
            this.finish()
        }

        btn_edit_date.setOnClickListener {
            DatePickerDialog(
                this,

                { _, y, moy, dom ->
                    selectedDate[Calendar.YEAR] = y
                    selectedDate[Calendar.MONTH] = moy
                    selectedDate[Calendar.DAY_OF_MONTH] = dom
                    date.text = selectedDate.dayAndMonth()
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btn_edit_time.setOnClickListener {
            TimePickerDialog(
                this, { _, hod, m ->
                    selectedDate[Calendar.HOUR_OF_DAY] = hod
                    selectedDate[Calendar.MINUTE] = m
                    time.text = selectedDate.hoursAndMinutes()
                },
                selectedDate[Calendar.HOUR_OF_DAY],
                selectedDate[Calendar.MINUTE],
                true
            ).show()
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
