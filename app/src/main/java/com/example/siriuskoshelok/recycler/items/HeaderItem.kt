package com.example.siriuskoshelok.recycler.items

class HeaderItem(val date: String) : BaseListItem() {
    override fun getType(): Int = HEADER
}