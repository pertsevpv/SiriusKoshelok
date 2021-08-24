package com.example.siriuskoshelok.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.utils.Constants

@Dao
interface CategoryDao {

    @Query("select * from ${Constants.CATEGORY_TABLE_NAME}")
    fun getAll(): List<Category>

    @Insert
    fun insertCategory(cat: Category)

}