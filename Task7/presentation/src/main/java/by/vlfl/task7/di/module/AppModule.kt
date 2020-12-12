package by.vlfl.task7.di.module

import android.content.Context
import by.vlfl.data.db.RateRoomDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): RateRoomDatabase {
        return RateRoomDatabase.getDatabase(context)
    }
}