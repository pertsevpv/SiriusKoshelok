package com.example.siriuskoshelok.ui.google

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.wallet.AllWalletsActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

const val GOOGLE_SIGN_IN_ACCOUNT_KEY = "GOOGLE_SIGN_IN_ACCOUNT"

class GoogleAuthorizationActivity : AppCompatActivity(R.layout.activity_google_authorization) {

    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toNextActivity()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val authorizeLauncher =
            registerForActivityResult(GoogleSignInAccountResultContract()) { result ->
                account = result?.getResult(ApiException::class.java)
                toNextActivity()
            }

        findViewById<SignInButton>(R.id.sign_in_button).setOnClickListener {
            authorizeLauncher.launch(mGoogleSignInClient)
        }
    }

    override fun onStart() {
        super.onStart()
        account = GoogleSignIn.getLastSignedInAccount(this)
        toNextActivity()
    }

    private fun toNextActivity() {
        if (account == null) return
        val intent = Intent(this, AllWalletsActivity::class.java)
        intent.putExtra(GOOGLE_SIGN_IN_ACCOUNT_KEY, account)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }
}
