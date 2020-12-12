package by.vlfl.data.network.api

import by.vlfl.data.db.RateEntity
import by.vlfl.data.network.model.RateNetData
import retrofit2.http.GET

interface ExRatesApiService {
    @GET("exrates/currencies")
    suspend fun getRates(): List<RateNetData>
}

fun List<RateNetData>.asRateEntity(): List<RateEntity> {
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