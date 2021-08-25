package com.example.siriuskoshelok.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.siriuskoshelok.utils.Constants

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class Category(
    @ColumnInfo(name = "pictureId") var pictureId: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "type") var type: Boolean? = null,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) var id: Long? = null
) {
    @Ignore
    fun typeName() = if (type == true) "Доход" else "Расход"
}