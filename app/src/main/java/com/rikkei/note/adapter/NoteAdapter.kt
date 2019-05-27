package com.rikkei.note.adapter

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.rikkei.note.R
import com.rikkei.note.data.model.Note
import com.rikkei.note.utils.StringUtils
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(val clickListener: (Note) -> Unit): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var notes = ArrayList<Note>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_note, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(notes.get(p1), clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(notes.get(position), clickListener)
    }
//
//    fun addNotes(notes: ArrayList<Note>){
//        this.notes.clear()
//        this.notes.addAll(notes)
//        notifyDataSetChanged()
//    }

    fun onNewData(newNotes: List<Note>) {
        val diffResult = DiffUtil.calculateDiff(NoteDiffCallback(this.notes, newNotes))
        this.notes.clear();
        this.notes.addAll(newNotes);
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        @SuppressLint("ResourceAsColor")
        fun bind(note: Note, clickListener: (Note) -> Unit) {
            val bg = StringUtils.getBackground(note.background)

            itemView.textTitle.text = note.title + " " + note.id
            itemView.textTitle.setTextColor(ContextCompat.getColor(itemView.context, bg!!.borderColor))
            itemView.textContent.text = note.content
            itemView.textContent.setTextColor(ContextCompat.getColor(itemView.context, bg!!.borderColor))

            itemView.textTime.text = note.time.toString()
            itemView.textTime.setTextColor(ContextCompat.getColor(itemView.context, bg!!.borderColor))

            itemView.setOnClickListener{clickListener(note)}

            itemView.constraintNote.setBackgroundColor(ContextCompat.getColor(itemView.context, bg!!.color))
            itemView.cardBorder.setCardBackgroundColor(ContextCompat.getColor(itemView.context, bg!!.borderColor))
        }
    }
}
