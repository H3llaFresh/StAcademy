package by.vlfl.task6_mvvm_arch

import android.app.Application
import by.vlfl.task6_mvvm_arch.di.DaggerApplicationComponent
import by.vlfl.task6_mvvm_arch.model.ExRatesApiService
import by.vlfl.task6_mvvm_arch.model.db.RateRoomDatabase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class MainApp : Application() {

    val applicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }
}