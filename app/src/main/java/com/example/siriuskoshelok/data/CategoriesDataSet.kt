package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

object CategoriesDataSet {

    val baseCategories: MutableList<CategoryItem> = mutableListOf(
        CategoryItem(
            Category(
                R.drawable.ic_salary,
                "Зарплата",
                true, 1
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_salary,
                "Подработка",
                true, 2
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_gift,
                "Подарок",
                true, 3
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_capitalisation,
                "Капитализация",
                true, 4
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_launch,
                "Кафе и рестораны",
                false, 5
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_market,
                "Супермаркеты",
                false, 6
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_sport,
                "Спортзал",
                false, 7
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_train,
                "Общественный транспор",
                false, 8
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_gas_station,
                "Бензин",
                false, 9
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_pharmacy,
                "Медицина",
                false, 10
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_house,
                "Квартплата",
                false, 11
            ), false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_travel,
                "Отпуск",
                false, 12
            ), false
        )
    )
}