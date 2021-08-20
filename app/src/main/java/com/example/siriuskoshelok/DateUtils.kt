package com.example.siriuskoshelok

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.dayAndMonth(): String = SimpleDateFormat("dd MMM").format(this)

@SuppressLint("SimpleDateFormat")
fun GregorianCalendar.dayAndMonth(): String =
    "${this[Calendar.DAY_OF_MONTH]} ${this[Calendar.MONTH]}"

fun GregorianCalendar.dayMonthYear(): GregorianCalendar =
    GregorianCalendar(this[Calendar.YEAR], this[Calendar.MONTH], this[Calendar.DAY_OF_MONTH])

@SuppressLint("SimpleDateFormat")
fun GregorianCalendar.hoursAndMinutes(): String =
    "${this[Calendar.HOUR]}:${Calendar.MINUTE}"
