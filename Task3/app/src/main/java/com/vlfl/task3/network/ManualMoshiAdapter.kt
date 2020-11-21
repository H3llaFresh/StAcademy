package com.vlfl.task3.network

import com.squareup.moshi.*
import com.vlfl.task3.db.Calculation
import java.lang.reflect.Type


class ManualMoshiAdapter(private val elementAdapter: JsonAdapter<Any?>) :
    JsonAdapter<List<Any?>>() {
    object Factory : JsonAdapter.Factory {
        override fun create(
            type: Type,
            annotations: Set<Annotation>,
            moshi: Moshi
        ): JsonAdapter<*>? {
            if (annotations.isNotEmpty() || Types.getRawType(type) != List::class.java) {
                return null
            }
            val elementType = Types.collectionElementType(type, List::class.java)
            val elementAdapter = moshi.adapter<Any?>(elementType)
            return ManualMoshiAdapter(elementAdapter).nullSafe()
        }
    }

    override fun fromJson(reader: JsonReader): List<Any?>? {
        val resultList = mutableListOf<Calculation>()
        reader.beginObject()
        while (reader.hasNext()) {
            if (reader.nextName().length == 20) {
                var variant = 0
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

                if (variant == 0 || task == "" || date == "" || resultNumber == 0) {
                    throw JsonDataException("Missing required field")
                }
                val calculation =
                    Calculation(variant = variant, task = task, date = date, result = resultNumber)
                resultList.add(calculation)

                reader.endObject()
            }
        }
        reader.endObject()
        return resultList
    }

    override fun toJson(writer: JsonWriter, value: List<Any?>?) {

        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginArray()
        for (i in value.indices) {
            elementAdapter.toJson(writer, value[i])
        }
        writer.endArray()
    }
}