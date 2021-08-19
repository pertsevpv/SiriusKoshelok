package com.example.siriuskoshelok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siriuskoshelok.data.OperationsDataSet
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.recycler.OperationAdapter
import com.example.siriuskoshelok.recycler.OperationDecoration
import java.util.*
import com.example.siriuskoshelok.ui.operation.AddSumActivity

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

        operationAdapter = OperationAdapter()
        recycler.apply {
            layoutManager = LinearLayoutManager(this@WalletActivity).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = operationAdapter
            addItemDecoration(OperationDecoration())
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

    private var backPress: Long = 0
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (backPress + 3000 > System.currentTimeMillis())
                this.finish()
            else Toast.makeText(
                baseContext, R.string.closing_warning,
                Toast.LENGTH_SHORT
            ).show()
            backPress = System.currentTimeMillis()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}