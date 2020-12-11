package by.vlfl.task6_mvvm

import android.app.Application
import by.vlfl.task6_mvvm.di.ApplicationComponent
import by.vlfl.task6_mvvm.di.DaggerApplicationComponent
import by.vlfl.task6_mvvm.model.ExRatesApiService
import by.vlfl.task6_mvvm.model.db.RateRoomDatabase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class MainApp : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}