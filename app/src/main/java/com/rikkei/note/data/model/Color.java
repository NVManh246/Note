package com.rikkei.note.data.model;

import android.support.annotation.StringDef;

@StringDef({Color.WHITE, Color.YELLOW, Color.BLUE,
        Color.GREEN, Color.RED})
public @interface Color {
    String WHITE = "Trắng";
    String YELLOW = "Vàng";
    String BLUE = "Xanh lam";
    String GREEN = "Xanh lá cây";
    String RED = "Đỏ";
}
