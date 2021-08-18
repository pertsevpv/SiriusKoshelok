package com.example.siriuskoshelok

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.dayAndMonth(): String = SimpleDateFormat("dd MMM").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.dayMonthYear(): String = SimpleDateFormat("dd MMM yyyy").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.hoursAndMinutes(): String = SimpleDateFormat("HH:mm").format(this)

fun today(): Date = Date()

@SuppressLint("SimpleDateFormat")
fun parseDayAndMonth(date: String): Date? = SimpleDateFormat("dd MMM").parse(date)

@SuppressLint("SimpleDateFormat")
fun parseDayMonthYear(date: String): Date? = SimpleDateFormat("dd MMM yyyy").parse(date)

fun Date.normalize(): Date? = parseDayMonthYear(this.dayMonthYear())