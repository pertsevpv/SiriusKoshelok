package com.example.siriuskoshelok

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.dayAndMonth(): String = SimpleDateFormat("dd MMM").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.hoursAndMinutes(): String = SimpleDateFormat("HH:mm").format(this)