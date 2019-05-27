package com.rikkei.note.view

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.rikkei.note.R
import com.rikkei.note.adapter.ItemDecoration
import com.rikkei.note.adapter.NoteAdapter
import com.rikkei.note.data.model.Note
import com.rikkei.note.utils.StringUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val noteAdapter = NoteAdapter { noteItem -> noteItemClick(noteItem) }
    var noteVM: NoteViewModel? = null
    var notesLive: LiveData<List<Note>>? = null
    var notes = ArrayList<Note>()
    var b = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteVM = ViewModelProviders.of(this)
            .get(NoteViewModel::class.java)
        notesLive = noteVM!!.getNotes()
        notesLive!!.observe(this, Observer<List<Note>> { t ->
            notes = t as ArrayList<Note>;

            sort(t)

            t?.let {
                noteAdapter.onNewData(it)
            }
        })

        setupView()
    }

    fun setupView() {
        toolbar.setTitle("Note")

        recyclerNote.adapter = noteAdapter
        recyclerNote.addItemDecoration(ItemDecoration(15))
        recyclerNote.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.getChildAt(0)?.top != 15) {
                    toolbar.elevation = 8F
                } else {
                    toolbar.elevation = 0F
                }
            }
        })

        fab.setOnClickListener { v ->

            startActivityForResult(Intent(this@MainActivity,
                NoteActivity::class.java), 111)
        }

        butonSort.setOnClickListener{v ->
            b = !b
            sort(notes)

            notes?.let {
                noteAdapter.onNewData(it)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 111 && resultCode == Activity.RESULT_OK) {
            val note = data!!.getParcelableExtra("note") as Note
            if(note.id != 0L) {
                noteVM!!.insert(note)
            }
        }
    }

    private fun noteItemClick(note: Note) {
        noteVM!!.delete(note)
    }

    private fun sort(notes: ArrayList<Note>) {
        Collections.sort(notes, object : Comparator<Note> {
            override fun compare(o1: Note?, o2: Note?): Int {
                val t1 = StringUtils.getPriority(o1!!.background)
                val t2 = StringUtils.getPriority(o2!!.background)
                if(b) {
                    if (t1 > t2) {
                        return -1
                    } else if (t1 < t2) {
                        return 1
                    }
                    return 0
                } else {
                    if (t1 > t2) {
                        return 1
                    } else if (t1 < t2) {
                        return -1
                    }
                    return 0
                }
            }
        })
        recyclerNote.scrollToPosition(0)
    }
}
