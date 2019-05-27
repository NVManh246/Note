package com.rikkei.note.data.source
import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.rikkei.note.data.model.Note

class NoteRepository(context: Context) {

    val noteDAO = AppDatabase.getAppDatabase(context)!!.noteDAO()

    fun insert(note: Note) {
        InsertNoteAsync(noteDAO)
            .execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsync(noteDAO)
            .execute(note)
    }

    fun insert(notes: ArrayList<Note>) {
        InsertAllNoteAsync(noteDAO)
            .execute(notes)
    }

    fun delete(note: Note) {
        DeleteNoteAsync(noteDAO)
            .execute(note)
    }

    fun delete(notes: List<Note>) {
        DeleteAllNoteAsync(noteDAO)
            .execute(notes)
    }

    fun getNotes(): LiveData<List<Note>> {
        return noteDAO.getAll()
    }

    class InsertNoteAsync(val noteDAO: NoteDAO): AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg params: Note?) {
            noteDAO.insert(params[0]!!)
        }
    }

    class InsertAllNoteAsync(val noteDAO: NoteDAO): AsyncTask<ArrayList<Note>, Unit, Unit>() {
        override fun doInBackground(vararg params: ArrayList<Note>?) {
            noteDAO.insert(params[0]!!)
        }

    }

    class UpdateNoteAsync(val noteDAO: NoteDAO): AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg params: Note?) {
            noteDAO.update(params[0]!!)
        }
    }

    class DeleteNoteAsync(val noteDAO: NoteDAO): AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg params: Note?) {
            noteDAO.delete(params[0]!!)
        }
    }

    class DeleteAllNoteAsync(val noteDAO: NoteDAO): AsyncTask<List<Note>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<Note>?) {
            noteDAO.delete(params[0]!!)
        }
    }
}
