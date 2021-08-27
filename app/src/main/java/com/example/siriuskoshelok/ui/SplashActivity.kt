package com.example.siriuskoshelok.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.ui.google.GoogleAuthorizationActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*SiriusApplication.instance.appDatabase.getOperationDao().clear()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {})
*/
/*        SiriusApplication.instance.appDatabase.getCategoryDao().clear()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("database: ", "clearAll")
            }, {
                Log.i("database: ", it.message ?: "")
            })*/

        /*SiriusApplication.instance.appDatabase.getWalletDao().clear()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {})
*/
        startActivity(Intent(this@SplashActivity, GoogleAuthorizationActivity::class.java))
        finish()
    }
}
