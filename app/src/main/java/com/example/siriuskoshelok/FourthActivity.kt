package com.example.siriuskoshelok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class FourthActivity : AppCompatActivity(R.layout.activity_fourth) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = intent.extras?.get(GOOGLE_SIGN_IN_ACCOUNT_KEY) as GoogleSignInAccount
        findViewById<TextView>(R.id.tmpTextView).text = account.email
    }
}