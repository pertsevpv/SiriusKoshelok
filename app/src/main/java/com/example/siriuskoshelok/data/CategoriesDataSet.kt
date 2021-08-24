package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

object CategoriesDataSet {

    val list: MutableList<CategoryItem> = mutableListOf<CategoryItem>().apply {
        addAll(baseCategories)
    }

    val baseCategories: List<CategoryItem> = listOf(
        CategoryItem(
            Category(
                R.drawable.ic_salary,
                "Зарплата",
                true
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_salary,
                "Подработка",
                true
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_gift,
                "Подарок",
                true
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_capitalisation,
                "Капитализация",
                true
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_launch,
                "Кафе и рестораны",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_market,
                "Супермаркеты",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_sport,
                "Спортзал",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_train,
                "Общественный транспор",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_gas_station,
                "Бензин",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_pharmacy,
                "Медицина",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_house,
                "Квартплата",
                false
            ),
            false
        ),
        CategoryItem(
            Category(
                R.drawable.ic_travel,
                "Отпуск",
                false
            ),
            false
        )
    )

}