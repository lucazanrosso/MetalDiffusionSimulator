package com.lucazanrosso.metaldiffusion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int lines = 10;
    public int columns = 20;
    int dim;
    public int totalVacancies = 10;
    View[][] views;
    Vacancy[] vacancies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start(new View(this));
    }

    public void start(View view2) {

        views = new View[columns][lines];
        vacancies = new Vacancy[totalVacancies];
        this.dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.removeAllViews();
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(
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
                Vacancy vacancy = new Vacancy(this, c, l);
                views[c][l] = vacancy;
                vacancies[totalVacancies - remainingVacancies] = vacancy;
                remainingVacancies--;
            }
        }

        for (int i = 0; i < columns; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(linearLayoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < lines; j++) {
                View view = new View(this);
                view.setLayoutParams(viewLayoutParams);
                if (views[i][j] instanceof Vacancy)
                    view.setBackgroundResource(0);
                else
                    if (i < columns / 2)
                        view.setBackgroundResource(R.drawable.blue_circle);
                    else
                        view.setBackgroundResource(R.drawable.red_circle);
                views[i][j] = view;
                linearLayout.addView(view);
            }
            mainLayout.addView(linearLayout);
        }
    }

//    public void play() {
//        Random r = new Random();
////        int remainingVacancies = totalVacancies;
//            int c = r.nextInt(4);
//            for (int i = 0; i < columns; i++)
//                for (int j = 0; j < lines; j++) {
//                    if (views[i][j] instanceof Vacancy) {
//                        switch (c) {
//                            case 0:
//
//                        }
//                    }
//                    switch (c) {
//                        case 0:
//                    }

//            for (Vacancy vacancy : vacancies)
//                if (vacancy.column == c && vacancies[v].line == l) {
//                    repeat = true;
//                    break;
//                }
//            if (! repeat) {
//                Vacancy vacancy = new Vacancy(this, c, l);
//                views[c][l] = vacancy;
//                vacancies[totalVacancies - remainingVacancies] = vacancy;
//                remainingVacancies--;
//            }
//        }
//    }
}