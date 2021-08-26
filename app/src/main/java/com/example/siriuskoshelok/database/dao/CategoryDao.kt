package com.example.siriuskoshelok.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("select * from ${Constants.CATEGORY_TABLE_NAME}")
    fun getAll(): Single<List<Category>>

    @Query("select * from ${Constants.CATEGORY_TABLE_NAME} where userLogin=:login")
    fun getByUserLogin(login: String = CurrentUser.login ?: ""): Single<List<Category>>

    @Insert
    fun insertCategory(cat: Category): Completable

    @Insert
    fun insertList(vararg list: Category): Completable

    @Query("delete from ${Constants.CATEGORY_TABLE_NAME}")
    fun clear(): Completable

}