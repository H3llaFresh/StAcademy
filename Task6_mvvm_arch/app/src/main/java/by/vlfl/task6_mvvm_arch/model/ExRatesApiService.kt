package by.vlfl.task6_mvvm_arch.model

import androidx.lifecycle.LiveData
import by.vlfl.task6_mvvm_arch.model.db.RateEntity
import retrofit2.http.GET

interface ExRatesApiService {
    @GET("exrates/currencies")
    suspend fun getRates(): List<Rate>
}

fun List<Rate>.asRateEntity(): List<RateEntity> {
    return map{
        RateEntity(
            it.curID,
            it.curParentID,
            it.curCode,
            it.curAbbreviation,
            it.curName,
            it.curNameBel,
            it.curNameEng,
            it.curQuotName,
            it.curQuotNameBel,
            it.curQuotNameEng,
            it.curNameMulti,
            it.curNameBelMulti,
            it.curNameEngMulti,
            it.curScale,
            it.curPeriodicity,
            it.curDateStart,
            it.curDateEnd,
        )
    }
}