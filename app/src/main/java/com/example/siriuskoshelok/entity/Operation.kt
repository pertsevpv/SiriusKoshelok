package com.example.siriuskoshelok.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.siriuskoshelok.data.CategoriesDataSet
import com.example.siriuskoshelok.utils.Constants
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = Constants.OPERATION_TABLE_NAME)
data class Operation(
    @ColumnInfo(name = "id") @field:Json(name = "id") @PrimaryKey(autoGenerate = false) var id: Long? = null,
    @ColumnInfo(name = "walletId") @field:Json(name = "walletId") var walletId: Long? = null,
    @ColumnInfo(name = "amount") @field:Json(name = "amount") var amount: Int? = null,
    @ColumnInfo(name = "categoryId") @field:Json(name = "categoryId") var categoryId: Long? = null,
    @ColumnInfo(name = "time") @field:Json(name = "executionDateLong") var timeMillis: Long? = null,
) : Parcelable {

    @Ignore
    fun getDate(): GregorianCalendar =
        GregorianCalendar().apply { this.time = Date(timeMillis ?: 0) }

    @Ignore
    fun getCategory(): Category? {
        return CategoriesDataSet.list.find { it.category.id == categoryId }?.category
    }
}