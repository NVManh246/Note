package com.rikkei.note.data.model

import android.arch.persistence.room.*
import android.os.Parcelable
import com.rikkei.note.converter.ListConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblNote")
@Parcelize
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "content")
    var content: String = "",
    @ColumnInfo(name = "time")
    var time: Long = 0,
    @ColumnInfo(name = "background")
    @Color
    var background: String = Color.WHITE
) : Parcelable {
    constructor(title: String, content: String, time: Long, background: String) : this() {
        this.title = title
        this.content = content
        this.time = time
        this.background = background
    }

    override fun equals(other: Any?): Boolean {
        val note = other as Note
        if(this.id == note.id) {
            return true
        }
        return false
    }
}
