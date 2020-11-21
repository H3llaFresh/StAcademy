package by.vlfl.task4

import android.app.Application
import androidx.room.RoomDatabase
import by.vlfl.task4.model.ExRatesApiService
import by.vlfl.task4.model.db.RateRoomDatabase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class MainApp: Application() {
    private val logging: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .build()
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    // https://www.nbrb.by/services/xmlexrates.aspx?ondate=01/31/2011
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.nbrb.by/api/")
            .build()
    }

    val ratesApiService: ExRatesApiService by lazy {
        retrofit.create(ExRatesApiService::class.java)
    }

    lateinit var rateDatabase: RateRoomDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        rateDatabase = RateRoomDatabase.getDatabase(this)
    }
    
    companion object {
        lateinit var instance: MainApp
    }
}