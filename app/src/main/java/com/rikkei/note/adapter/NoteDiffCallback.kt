package com.rikkei.note.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.rikkei.note.data.model.Note

class NoteDiffCallback(var oldNotes: List<Note>, var newNotes: List<Note>)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldNotes.get(p0).equals(newNotes.get(p1))
    }

    override fun getOldListSize(): Int {
        return oldNotes.size
    }

    override fun getNewListSize(): Int {
        return newNotes.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return oldNotes.get(p0).id == newNotes.get(p1).id
    }

//    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
//        val diff = ArrayList<Note>()
//        val newNote = newNotes.get(newItemPosition)
//        val oldNote = oldNotes.get(oldItemPosition)
//        if(newNote.equals(oldNote)){
//            diff.add(newNote)
//        }
//        return diff
//    }
}