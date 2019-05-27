package com.rikkei.note.view

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import com.rikkei.note.R
import com.rikkei.note.adapter.BgAdapter
import com.rikkei.note.adapter.ItemDecoration
import com.rikkei.note.data.model.Background
import com.rikkei.note.data.model.Color
import com.rikkei.note.data.model.Note
import com.rikkei.note.utils.StringUtils
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*

class NoteActivity : AppCompatActivity() {

    var bgs = ArrayList<Background>()
    var color = ""

    init {
        bgs.addAll(StringUtils.bgs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        setContentView(R.layout.activity_note)

        buttonBack.setOnClickListener{v -> onBack()}
        buttonOk.setOnClickListener{v -> insertNode()}

        reyclerBg.adapter = BgAdapter(bgs, {bg -> setColor(bg)})
        reyclerBg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        reyclerBg.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                with(outRect) {
                    if(itemPosition == 0) {
                        left = 20
                    }
                    right = 20
                    top = 20
                    bottom = 20
                }
            }


        })

        val sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.isHideable = true;
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;
        buttonBackground.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }

        editContent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;
                return false
            }
        })
    }

    private fun setColor(bg: Background) {
        color = bg.name
        ContextCompat.getColor(this, bg.borderColor).apply {
            editTitle.setTextColor(this)
            editContent.setTextColor(this)
            view.setBackgroundColor(this)
            view_two.setBackgroundColor(this)
            findViewById<TextView>(R.id.textTime).setTextColor(this)
            buttonBack.setColorFilter(this)
            buttonOk.setColorFilter(this)
            buttonBackground.setColorFilter(this)
            buttonInsertImage.setColorFilter(this)
        }

        ContextCompat.getColor(this, bg.color).apply {
            constraintNote.setBackgroundColor(this)
            toolbar.setBackgroundColor(this)
            window.statusBarColor = this
        }
    }

    private fun insertNode() {
        val title = editTitle.text.toString()
        val content = editContent.text.toString()
        val time = System.currentTimeMillis()

        val note = Note(title, content, time, color)
        Intent().apply {
            putExtra("note", note)
            setResult(Activity.RESULT_OK, this)
        }
        finish()
    }

    private fun onBack() {
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBack()
    }
}
