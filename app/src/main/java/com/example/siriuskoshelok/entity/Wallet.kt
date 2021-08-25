package com.example.siriuskoshelok.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.siriuskoshelok.data.OperationDataSet
import com.example.siriuskoshelok.utils.Constants
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Constants.WALLET_TABLE_NAME)
@Parcelize
data class Wallet(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) @field:Json(name = "id") var walletId: Long? = null,
    @ColumnInfo(name = "name") @field:Json(name = "name") var name: String? = "",
    @ColumnInfo(name = "limit") @field:Json(name = "limit") var limit: Int? = null,
    @Ignore val operationList: MutableList<Operation> = mutableListOf()
) : Parcelable {

    @Ignore
    fun countExpense(): Int =
        operationList
            .filter { it.getCategory()?.type == false }
            .map { it.amount ?: 0 }
            .fold(0) { a, b -> a + b }

    @Ignore
    fun countIncome(): Int =
        operationList
            .filter { it.getCategory()?.type == true }
            .map { it.amount ?: 0 }
            .fold(0) { a, b -> a + b }

    @Ignore
    fun countMoney(): Int =
        countIncome() - countExpense()

}
