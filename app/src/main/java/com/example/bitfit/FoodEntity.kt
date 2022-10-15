package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity(
    @ColumnInfo(name = "foodname") val foodname: String,
    @ColumnInfo(name = "calories") val calories: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)