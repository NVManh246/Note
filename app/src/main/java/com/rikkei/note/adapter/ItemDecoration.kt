package com.rikkei.note.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecoration(var sp: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect){
            top = sp
            bottom = sp
            left = sp
            right = sp
        }
    }
}
