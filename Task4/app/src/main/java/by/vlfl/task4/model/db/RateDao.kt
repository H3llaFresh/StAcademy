package by.vlfl.task4.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RateDao {
    @Query("SELECT * FROM rates_table")
    suspend fun getDatabaseRates(): List<RateEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRates(rates: List<RateEntity>)
}