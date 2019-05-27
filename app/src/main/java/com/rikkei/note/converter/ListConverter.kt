package com.rikkei.note.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikkei.note.data.model.Note
import java.util.*
import kotlin.collections.ArrayList

class ListConverter {
    var gson = Gson()

    fun toJson(photos: ArrayList<String>?): String? {
        return gson.toJson(photos)
    }

    fun toObject(data: String?): List<String>? {
        if(data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }
}
