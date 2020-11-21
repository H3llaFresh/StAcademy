package com.vlfl.task3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CalculationDao {
    @Query ("SELECT * FROM calculation_table")
    suspend fun getAllCalculations(): List<Calculation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCalculation(calculation: Calculation)
}