package com.vlfl.task3.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.vlfl.task3.db.Calculation

class ManualParser {
    fun parse(reader: JsonReader): List<Calculation> {
        val resultList = mutableListOf<Calculation>()

        reader.beginObject()
        while (reader.hasNext()) {
            if (reader.nextName().length == 20) {
                reader.beginObject()
                while (reader.hasNext()) {
                    var variant: Int = 0
                    var task = ""
                    var date = ""
                    var resultNumber = 0
                    reader.beginObject()
                    while (reader.hasNext()) {
                        when (reader.nextName()) {
                            "variant" -> variant = reader.nextInt()
                            "task" -> task = reader.nextString()
                            "date" -> date = reader.nextString()
                            "result" -> resultNumber = reader.nextInt()
                            else -> reader.skipValue()
                        }
                    }
                    reader.endObject()

                    if (variant == 0 || task == "" || date == "" || resultNumber == 0) {
                        throw JsonDataException("Missing required field")
                    }
                    val calculation = Calculation(variant = variant, task = task, date = date, result = resultNumber)
                    resultList.add(calculation)
                }
                reader.endObject()
            }
        }
        reader.endObject()
        return resultList
    }
}
