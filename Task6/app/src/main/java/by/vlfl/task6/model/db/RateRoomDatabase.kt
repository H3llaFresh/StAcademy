package by.vlfl.task6.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RateEntity::class], version = 1, exportSchema = false)
abstract class RateRoomDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao

    companion object {
        @Volatile
        private var INSTANCE: RateRoomDatabase? = null

        fun getDatabase(context: Context): RateRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RateRoomDatabase::class.java,
                    "rates_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}