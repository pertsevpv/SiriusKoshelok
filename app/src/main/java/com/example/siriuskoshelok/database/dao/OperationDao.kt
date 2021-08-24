package com.example.siriuskoshelok.database.dao

import androidx.room.*
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.utils.Constants

@Dao
interface OperationDao {

    @Query("select * from ${Constants.OPERATION_TABLE_NAME}")
    fun getAll(): List<Operation>

    @Insert
    fun insertOperation(op: Operation)

    @Delete
    fun deleteOperation(op: Operation)

    @Update
    fun updateOperation(op: Operation)
}