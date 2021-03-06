package com.example.siriuskoshelok.ui.google

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.siriuskoshelok.utils.Constants
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.User
import com.example.siriuskoshelok.ui.wallet.AllWalletsActivity
import com.example.siriuskoshelok.utils.dayAndMonth
import com.example.siriuskoshelok.utils.dayMonthYear
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
                CurrentUser.login = account?.email
                SiriusApplication.instance.userApiService
                    .register(CurrentUser.login ?: "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("api: ", "Registration - Success ${CurrentUser.login}")
                        toNextActivity()
                    }, {
                        Log.i("api: ", "Registration - Fail")
                    })
            }

        sign_in_button.setOnClickListener {
            authorizeLauncher.launch(mGoogleSignInClient)
        }
    }

    override fun onStart() {
        super.onStart()
        account = GoogleSignIn.getLastSignedInAccount(this)
        CurrentUser.login = account?.email
        toNextActivity()
    }

    @SuppressLint("CheckResult")
    private fun toNextActivity() {
        if (account == null || account?.email == null) return
        Log.i("cur login: ", CurrentUser.login ?: "")
        val intent = Intent(this, AllWalletsActivity::class.java)
        intent.putExtra(Constants.GOOGLE_SIGN_IN_ACCOUNT_KEY, account)
        startActivity(intent)
        finish()
    }
}
