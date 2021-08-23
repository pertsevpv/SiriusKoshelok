package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.entity.CategoryResponse
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

object CategoriesDataSet {
    val listCategory: MutableList<CategoryResponse> = mutableListOf(
        CategoryResponse(
            Category(R.drawable.ic_salary,
            "Зарплата",
            "Доход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_salary,
            "Подработка",
            "Доход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_gift,
            "Подарок",
            "Доход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_capitalisation,
            "Капитализация",
            "Доход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_launch,
            "Кафе и рестораны",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_market,
            "Супермаркеты",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_sport,
            "Спортзал",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_train,
            "Общественный транспор",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_gas_station,
            "Бензин",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_pharmacy,
            "Медицина",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_house,
            "Квартплата",
            "Расход"),
            false
        ),
        CategoryResponse(
            Category(R.drawable.ic_travel,
            "Отпуск",
            "Расход"),
            false
        )
    )
}