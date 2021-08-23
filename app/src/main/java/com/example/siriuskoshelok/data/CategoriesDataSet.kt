package com.example.siriuskoshelok.data

import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.R
import com.example.siriuskoshelok.entity.Category

object CategoriesDataSet {
    val listCategory: MutableList<CategoryItem> = mutableListOf(
        CategoryItem(
            Category(R.drawable.ic_salary,
                "Зарплата",
                true),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_salary,
                "Подработка",
                true),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_gift,
                "Подарок",
                true),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_capitalisation,
                "Капитализация",
                true),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_launch,
                "Кафе и рестораны",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_market,
                "Супермаркеты",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_sport,
                "Спортзал",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_train,
                "Общественный транспор",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_gas_station,
                "Бензин",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_pharmacy,
                "Медицина",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_house,
                "Квартплата",
                false),
            false
        ),
        CategoryItem(
            Category(R.drawable.ic_travel,
                "Отпуск",
                false),
            false
        )
    )
}





//object CategoriesDataSet {
//    val listCategory: MutableList<CategoryItem> = mutableListOf(
//        CategoryItem(
//            Category(R.drawable.ic_salary,
//            "Зарплата",
//            "Доход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_salary,
//            "Подработка",
//            "Доход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_gift,
//            "Подарок",
//            "Доход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_capitalisation,
//            "Капитализация",
//            "Доход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_launch,
//            "Кафе и рестораны",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_market,
//            "Супермаркеты",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_sport,
//            "Спортзал",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_train,
//            "Общественный транспор",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_gas_station,
//            "Бензин",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_pharmacy,
//            "Медицина",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_house,
//            "Квартплата",
//            "Расход"),
//            false
//        ),
//        CategoryItem(
//            Category(R.drawable.ic_travel,
//            "Отпуск",
//            "Расход"),
//            false
//        )
//    )
//}