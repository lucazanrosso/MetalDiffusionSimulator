package com.lucazanrosso.metaldiffusion;

import android.content.Context;
import android.view.View;

public class Atom extends View {

    public int type;
    public int column;
    public int line;

    public Atom(Context context, int column, int line) {
        super(context);
        this.column = column;
        this.line = line;
    }

    public Atom(Context context) {
        super(context);
    }

    public void setColor(int type) {
        this.type = type;
        if (type == 0)
            this.setBackgroundResource(R.drawable.blue_circle);
        else
            this.setBackgroundResource(R.drawable.red_circle);
    }

    public int getColor() {
        return this.type;
    }
}