package com.example.siriuskoshelok.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.dayAndMonth(): String = SimpleDateFormat("dd MMM").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.dayMonthYear(): String = SimpleDateFormat("dd-MM-yyyy").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.hoursAndMinutes(): String = SimpleDateFormat("HH:mm").format(this)

@SuppressLint("SimpleDateFormat")
fun GregorianCalendar.dayAndMonth(): String = this.time.dayAndMonth()

fun GregorianCalendar.dayMonthYear(): GregorianCalendar =
    GregorianCalendar(this[Calendar.YEAR], this[Calendar.MONTH], this[Calendar.DAY_OF_MONTH])

fun GregorianCalendar.hoursAndMinutes(): String = this.time.hoursAndMinutes()
