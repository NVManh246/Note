package com.rikkei.note.data.source

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.rikkei.note.data.model.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: ArrayList<Note>)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Delete
    fun delete(notes: List<Note>)

    @Query("SELECT * FROM tblNote")
    fun getAll(): LiveData<List<Note>>
}
