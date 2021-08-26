package com.example.siriuskoshelok.ui.operation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.*
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.utils.dayAndMonth
import com.example.siriuskoshelok.utils.hoursAndMinutes
import kotlinx.android.synthetic.main.activity_add_operation.*
import java.util.*

class AddOperationActivity : AppCompatActivity(R.layout.activity_add_operation) {

    private val presenter = AddOperationPresenter(this)

    @SuppressLint("SetTextI18n")
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
            presenter.onClickedCreate()
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
                this, R.style.Picker, { _, hod, m ->
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
