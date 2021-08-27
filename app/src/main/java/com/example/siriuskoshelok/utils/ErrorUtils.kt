package com.example.siriuskoshelok.utils

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.app.SiriusApplication
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.layout_error.view.*
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException

@Parcelize
data class ErrorMes(
    @field:Json(name = "message") val message: String
) : Parcelable

object ErrorUtils {

    @SuppressLint("InflateParams")
    fun showMessage(e: Throwable, activity: AppCompatActivity) {
        var mes: String? = null
        if (e is HttpException) {
            runCatching {
                val errorConverter: Converter<ResponseBody, ErrorMes> =
                    SiriusApplication.instance.appRetrofit.responseBodyConverter(
                        ErrorMes::class.java, arrayOfNulls<Annotation>(0)
                    )
                mes = errorConverter.convert(e.response()?.errorBody()!!)?.message
            }.onFailure {
                mes = e.response()?.body().toString()
            }
        }
        if (mes.isNullOrEmpty()) mes = e.message

        val layout: View = activity.layoutInflater.inflate(R.layout.layout_error, null)
        val text = layout.findViewById<View>(R.id.text_error) as TextView
        val img = layout.img_error
        text.text = mes
        text.width = 900
        img.setImageResource(R.drawable.ic_error)
        Toast(activity).apply {
            duration = Toast.LENGTH_LONG
            view = layout
            setGravity(Gravity.TOP, 0, 0)
        }.show()
    }
}
