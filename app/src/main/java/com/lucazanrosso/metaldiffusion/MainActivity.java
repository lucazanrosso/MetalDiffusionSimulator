package com.lucazanrosso.metaldiffusion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int lines = 10;
    public int columns = 20;
    int dim;
    public int totalVacancies = 20;
    Atom[][] atoms;
    Atom[] vacancies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start(new View(this));
    }

    public void start(View view2) {

        atoms = new Atom[columns][lines];
        vacancies = new Atom[totalVacancies];
        this.dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.removeAllViews();
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams atomLayoutParams = new LinearLayout.LayoutParams(
                dim, dim);

        Random r = new Random();
        int remainingVacancies = totalVacancies;
        boolean repeat;
        while (remainingVacancies != 0) {
            repeat = false;
            int c = r.nextInt(columns);
            int l = r.nextInt(lines);
            for (int v = 0; v + remainingVacancies < totalVacancies; v++)
                if (vacancies[v].column == c && vacancies[v].line == l) {
                    repeat = true;
                    break;
                }
            if (! repeat) {
                Atom vacancy = new Atom(this, c, l);
                atoms[c][l] = vacancy;
                vacancies[totalVacancies - remainingVacancies] = vacancy;
                remainingVacancies--;
            }
        }
        for (int i = 0; i < columns; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(linearLayoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < lines; j++) {
                Atom atom = new Atom(this);
                atom.setLayoutParams(atomLayoutParams);
                if (atoms[i][j] != null)
                    atom.setBackgroundResource(0);
                else
                    if (i < columns / 2)
                        atom.setColor(0);
                    else
                        atom.setColor(1);
                atoms[i][j] = atom;
                linearLayout.addView(atom);
            }
            mainLayout.addView(linearLayout);
        }
    }

    public void play(View view) {
        Random r = new Random();
        for (Atom vacancy : vacancies) {
            int c = vacancy.column;
            int l = vacancy.line;
            int newC = c;
            int newL = l;
            int n = r.nextInt(4);
            switch (n) {
                case 0:
                    if (l != 0)
                        newL--;
                    break;
                case 1:
                    if (c != columns - 1)
                        newC++;
                    break;
                case 2:
                    if (l != lines - 1)
                        newL++;
                    break;
                case 3:
                    if (c != 0)
                        newC--;
                    break;
            }
            atoms[c][l].setColor(atoms[newC][newL].getColor());
            atoms[newC][newL].setBackgroundResource(0);
            vacancy.column = newC;
            vacancy.line = newL;
        }
    }
}