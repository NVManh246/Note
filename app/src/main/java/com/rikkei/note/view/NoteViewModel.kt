package com.rikkei.note.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rikkei.note.data.model.Note
import com.rikkei.note.data.source.NoteRepository

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val noteRepository: NoteRepository
    val notesLive: LiveData<List<Note>>

    init {
        noteRepository = NoteRepository(application)
        notesLive = noteRepository.getNotes()
    }

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun insert(notes: ArrayList<Note>) {
        noteRepository.insert(notes)
    }

    fun update(note: Note) {
        noteRepository.update(note)
    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }

    fun deleteAllNote(notes: List<Note>) {
        noteRepository.delete(notes)
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLive
    }
}