package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.R

object CategoriesDataSet {
    val listCategory: MutableList<Category> = mutableListOf(
        Category(
            R.drawable.ic_salary,
            "Зарплата",
            "Доход",
            false
        ),
        Category(
            R.drawable.ic_salary,
            "Подработка",
            "Доход",
            false
        ),
        Category(
            R.drawable.ic_gift,
            "Подарок",
            "Доход",
            false
        ),
        Category(
            R.drawable.ic_capitalisation,
            "Капитализация",
            "Доход",
            false
        ),
        Category(
            R.drawable.ic_launch,
            "Кафе и рестораны",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_market,
            "Супермаркеты",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_sport,
            "Спортзал",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_train,
            "Общественный транспор",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_gas_station,
            "Бензин",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_pharmacy,
            "Медицина",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_house,
            "Квартплата",
            "Расход",
            false
        ),
        Category(
            R.drawable.ic_travel,
            "Отпуск",
            "Расход",
            false
        )
    )
}