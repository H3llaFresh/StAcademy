package com.vlfl.task3.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="calculation_table")
data class Calculation(
    @PrimaryKey(autoGenerate = true) val calculation_id: Int = 0,
    val variant: Int,
    val task: String,
    val date: String,
    val result: Int
)
