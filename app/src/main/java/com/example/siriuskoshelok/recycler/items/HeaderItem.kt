package com.example.siriuskoshelok.recycler.items

import java.util.*

class HeaderItem(val date: GregorianCalendar) : BaseListItem() {
    override fun getType(): Int = HEADER

    override fun toString(): String = date.toString()

    override fun getDateLong(): Long = date.timeInMillis
}