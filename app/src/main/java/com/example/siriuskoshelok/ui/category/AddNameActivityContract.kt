package com.example.siriuskoshelok.ui.category

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class AddNameActivityContract : ActivityResultContract<Int, String?>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, AddNameActivity::class.java).apply {
            putExtra(AddNameActivity.INPUT_KEY, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(AddNameActivity.OUTPUT_NAME)
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }
}