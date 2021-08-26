package com.example.siriuskoshelok.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.siriuskoshelok.utils.Constants
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class Category(
    @ColumnInfo(name = "pictureId") @field:Json(name = "pictureId") var pictureId: Int? = null,
    @ColumnInfo(name = "name") @field:Json(name = "name") var name: String? = null,
    @ColumnInfo(name = "type") @field:Json(name = "income") var type: Boolean? = null,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) @field:Json(name = "id") var id: Long? = null,
    @Transient @ColumnInfo(name = "userLogin") var userLogin: String? = ""
):Parcelable {
    @Ignore
    fun typeName() = if (type == true) "Доход" else "Расход"
}