package com.rikkei.note.utils

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.rikkei.note.R
import com.rikkei.note.data.model.Background
import com.rikkei.note.data.model.Color

class StringUtils {
    companion object {
        val bgs = ArrayList<Background>().apply {
            this.add(Background(Color.WHITE, R.color.color_white, R.color.color_border_white))
            this.add(Background(Color.YELLOW, R.color.color_yellow, R.color.color_border_yellow))
            this.add(Background(Color.BLUE, R.color.color_blue, R.color.color_border_blue))
            this.add(Background(Color.GREEN, R.color.color_green, R.color.color_border_green))
            this.add(Background(Color.RED, R.color.color_red, R.color.color_border_red))
        }

        fun getBackground(@Color name: String): Background? {
            when (name) {
                Color.WHITE -> return bgs.get(0)
                Color.YELLOW -> return bgs.get(1)
                Color.BLUE -> return bgs.get(2)
                Color.GREEN -> return bgs.get(3)
                Color.RED -> return bgs.get(4)
            }
            return bgs.get(0)
        }

        fun getPriority(@Color name: String): Int {
            when (name) {
                Color.WHITE -> return 0
                Color.YELLOW -> return 1
                Color.BLUE -> return 2
                Color.GREEN -> return 3
                Color.RED -> return 4
            }
            return 0
        }
    }
}


