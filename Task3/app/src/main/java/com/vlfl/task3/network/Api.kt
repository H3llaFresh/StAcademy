package com.vlfl.task3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vlfl.task3.db.Calculation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.IOException

interface NetworkApiService {
    @GET(
        "/{FIO}.json"
    )
    suspend fun getCalculations(@Path("FIO") name: String): List<Calculation>

    @POST(
        "/{FIO}.json"
    )
    suspend fun sendCalculations(@Path("FIO") name: String, @Body requestBody: CalculationNetwork)

    object NetworkApi {
        private val moshi = Moshi.Builder()
            .add(ManualMoshiAdapter.Factory)
            .add(KotlinJsonAdapterFactory())
            .build()
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://st-academy-9f9d8.firebaseio.com")
            .build()

        private val networkService = retrofit.create(
            NetworkApiService::class.java
        )

        suspend fun getCalculationsFromNetwork(name: String): List<Calculation> {
            var networkCalculation: List<Calculation> = listOf()
            try {
                withContext(Dispatchers.IO) {
                    networkCalculation = networkService.getCalculations(name)
                }
            } catch (error: IOException) {

            }

            return networkCalculation
        }

        suspend fun sendCalculationsToNetwork(
            name: String,
            calculationNetwork: CalculationNetwork
        ): Boolean {
            var noErrorOccurred = true
            try {
                withContext(Dispatchers.IO) {
                    networkService.sendCalculations(name, calculationNetwork)
                }
            } catch (error: IOException) {
                noErrorOccurred = false
                println("No network")
            }
            return noErrorOccurred
        }
    }
}