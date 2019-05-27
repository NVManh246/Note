package com.rikkei.note.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.note.R
import com.rikkei.note.data.model.Background
import kotlinx.android.synthetic.main.item_bg.view.*

class BgAdapter(
    private val items: ArrayList<Background>,
    private val itemClickListener: (Background) -> Unit)
    : RecyclerView.Adapter<BgAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_bg, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items.get(p1), itemClickListener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(bg: Background, itemClickListener: (Background) -> Unit) {
            itemView.border.setBackgroundColor(ContextCompat.getColor(itemView.context, bg.borderColor))
            itemView.bg.setBackgroundColor(ContextCompat.getColor(itemView.context, bg.color))
            itemView.textNameBg.text = bg.name
            itemView.textCenter.setTextColor(ContextCompat.getColor(itemView.context, bg.borderColor))
            itemView.setOnClickListener{itemClickListener(bg)}
        }
    }
}