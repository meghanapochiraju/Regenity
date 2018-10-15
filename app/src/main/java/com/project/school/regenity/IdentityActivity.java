package com.project.school.regenity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class IdentityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PopupWindow popUp = new PopupWindow(this);
        final ConstraintLayout layout = new ConstraintLayout(this);


            CalendarView calendarView = (CalendarView) findViewById(R.id.CV);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                getReminders();
            }
        });
    }

    public void getReminders(){
        Intent intent = new Intent(IdentityActivity.this, RemindersActivity.class);
        IdentityActivity.this.startActivity(intent);
    }


}
