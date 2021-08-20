package com.example.siriuskoshelok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.data.OperationsDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.recycler.OperationAdapter
import com.example.siriuskoshelok.recycler.OperationDecoration
import java.util.*
import com.example.siriuskoshelok.ui.operation.AddSumActivity
import kotlin.system.exitProcess

class WalletActivity : AppCompatActivity() {

    private val recycler by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<RecyclerView>(R.id.operations_recycler_view)
    }

    private lateinit var operationAdapter: OperationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btnAddOperation: Button = findViewById(R.id.btn_add_operation)
        val textEmpty: TextView = findViewById(R.id.empty_view)

        operationAdapter = OperationAdapter()
        recycler.apply {
            layoutManager = LinearLayoutManager(this@WalletActivity).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = operationAdapter
            addItemDecoration(OperationDecoration())
        }
        if(OperationsDataSet.list.isEmpty()){
            recycler.isVisible = false
            textEmpty.isVisible = true
        }
        operationAdapter.setData(OperationsDataSet.list)
        btnAddOperation.setOnClickListener {
            OperationsDataSet.list.add(Operation(null, null, null, null))
            val intent = Intent(this, AddSumActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            closeApp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        closeApp()
    }

    private val LIMIT_SECOND: Long = 3000
    private var doubleBack = 1
    private fun closeApp() {
        if (doubleBack == 2) {
            finishAffinity()
            exitProcess(0)
        } else {
            doubleBack++;
            Toast.makeText(this, R.string.closing_warning, Toast.LENGTH_SHORT).show()
        }
        Handler().postDelayed({ doubleBack = 1; }, LIMIT_SECOND)
    }
}