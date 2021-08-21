package com.example.siriuskoshelok.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.siriuskoshelok.R

class AddNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_name)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_name)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val btnAddName: Button = findViewById(R.id.btn_add_name)
        val editName: EditText = findViewById(R.id.edit_name)
        editName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btnAddName.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }
        })
        btnAddName.setOnClickListener {
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
}