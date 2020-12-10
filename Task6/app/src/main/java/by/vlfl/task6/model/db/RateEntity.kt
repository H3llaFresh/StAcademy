package by.vlfl.task6.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_table")
class RateEntity(
    @PrimaryKey(autoGenerate = true) val curID: Int = 0,
    val curParentID: Int,
    val curCode: String,
    val curAbbreviation: String,
    val curName: String,
    val curNameBel: String,
    val curNameEng: String,
    val curQuotName: String,
    val curQuotNameBel: String,
    val curQuotNameEng: String,
    val curNameMulti: String,
    val curNameBelMulti: String,
    val curNameEngMulti: String,
    val curScale: Int,
    val curPeriodicity: Int,
    val curDateStart: String,
    val curDateEnd: String
)
