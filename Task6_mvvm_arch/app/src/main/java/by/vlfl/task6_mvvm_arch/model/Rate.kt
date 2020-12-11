package by.vlfl.task6_mvvm_arch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rate(
    @Json(name = "Cur_ID")
    val curID: Int,
    @Json(name = "Cur_ParentID")
    val curParentID: Int,
    @Json(name = "Cur_Code")
    val curCode: String,
    @Json(name = "Cur_Abbreviation")
    val curAbbreviation: String,
    @Json(name = "Cur_Name")
    val curName: String,
    @Json(name = "Cur_Name_Bel")
    val curNameBel: String,
    @Json(name = "Cur_Name_Eng")
    val curNameEng: String,
    @Json(name = "Cur_QuotName")
    val curQuotName: String,
    @Json(name = "Cur_QuotName_Bel")
    val curQuotNameBel: String,
    @Json(name = "Cur_QuotName_Eng")
    val curQuotNameEng: String,
    @Json(name = "Cur_NameMulti")
    val curNameMulti: String,
    @Json(name = "Cur_Name_BelMulti")
    val curNameBelMulti: String,
    @Json(name = "Cur_Name_EngMulti")
    val curNameEngMulti: String,
    @Json(name = "Cur_Scale")
    val curScale: Int,
    @Json(name = "Cur_Periodicity")
    val curPeriodicity: Int,
    @Json(name = "Cur_DateStart")
    val curDateStart: String,
    @Json(name = "Cur_DateEnd")
    val curDateEnd: String
)