package com.example.siriuskoshelok.database.dao

import androidx.room.*
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface OperationDao {

    @Query("select * from ${Constants.OPERATION_TABLE_NAME}")
    fun getAll(): Single<List<Operation>>

    @Query("select * from ${Constants.OPERATION_TABLE_NAME} where walletId=:wallerId")
    fun getByWalletId(wallerId: Long): Single<List<Operation>>

    @Insert
    fun insertOperation(op: Operation): Completable

    @Delete
    fun deleteOperation(op: Operation): Completable

    @Update
    fun updateOperation(op: Operation): Completable

    @Query("delete from ${Constants.OPERATION_TABLE_NAME}")
    fun clear():Completable
}