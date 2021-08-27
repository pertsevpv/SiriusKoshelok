package com.example.siriuskoshelok.database.dao

import androidx.room.*
import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.Wallet
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface WalletDao {

    @Query("select * from ${Constants.WALLET_TABLE_NAME}")
    fun getAll(): Single<List<Wallet>>

    @Query("select * from ${Constants.WALLET_TABLE_NAME} where userLogin=:userLogin")
    fun getByUserLogin(userLogin: String = CurrentUser.login ?: ""): Single<List<Wallet>>

    @Insert
    fun insertWallet(op: Wallet): Completable

    @Insert
    fun insertWalletList(vararg list: Wallet): Completable

    @Delete
    fun deleteWallet(op: Wallet): Completable

    @Update
    fun updateWallet(op: Wallet): Completable

    @Query("delete from ${Constants.WALLET_TABLE_NAME}")
    fun clear(): Completable

    @Query("delete from ${Constants.WALLET_TABLE_NAME} where userLogin=:userLogin")
    fun clearByLogin(userLogin: String = CurrentUser.login ?: ""): Completable
}