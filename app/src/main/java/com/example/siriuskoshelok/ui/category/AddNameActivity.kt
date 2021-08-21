package com.example.siriuskoshelok.ui.category

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.example.siriuskoshelok.R
import kotlinx.android.synthetic.main.activity_add_name.*

class AddNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_name)
        setSupportActionBar(toolbar_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        edit_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_add_name.isEnabled = s.toString().isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) = Unit

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) = Unit
        })
        btn_add_name.setOnClickListener {
            val dataIntent = Intent()
            dataIntent.putExtra(AddNameActivity.OUTPUT_NAME, edit_name.text.toString())
            setResult(Activity.RESULT_OK, dataIntent)
            this.finish()
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

    companion object {
        const val INPUT_KEY = "input_name"
        const val OUTPUT_NAME = "output_name"
    }
}