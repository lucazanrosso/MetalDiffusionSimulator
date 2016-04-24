package com.lucazanrosso.metaldiffusion;

import android.content.Context;
import android.view.View;

public class Vacancy extends View {

    public int column;
    public int line;

    public Vacancy(Context context, int column, int line) {
        super(context);
        this.column = column;
        this.line = line;
    }
}
