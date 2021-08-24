package com.example.siriuskoshelok.database.dao

import androidx.room.*
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface WalletDao {

    @Query("select * from ${Constants.WALLET_TABLE_NAME}")
    fun getAll(): Single<List<Wallet>>

    @Insert
    fun insertWallet(op: Wallet): Completable

    @Delete
    fun deleteWallet(op: Wallet): Completable

    @Update
    fun updateWallet(op: Wallet): Completable
}