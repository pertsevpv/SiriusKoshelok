package com.example.siriuskoshelok.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.siriuskoshelok.database.dao.CategoryDao
import com.example.siriuskoshelok.database.dao.OperationDao
import com.example.siriuskoshelok.database.dao.WalletDao
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet

@Database(
    entities = [Wallet::class,
        Operation::class,
        Category::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    abstract fun getOperationDao(): OperationDao

    abstract fun getWalletDao(): WalletDao
}