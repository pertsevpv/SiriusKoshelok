package com.example.siriuskoshelok.ui.google

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.User
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.utils.dayAndMonth
import com.example.siriuskoshelok.utils.dayMonthYear
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_google_authorization.*
import java.util.*

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

        sign_in_button.setOnClickListener {
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
        val user: User =
            User(account?.email, account?.givenName, account?.familyName, Date().dayMonthYear())

        val intent = Intent(this, AllWalletsActivity::class.java)
        //intent.putExtra(Constants.GOOGLE_SIGN_IN_ACCOUNT_KEY, account)
        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }
}
