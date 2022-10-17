package com.vbgroups.vbtantrav1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

public class calendar extends AppCompatActivity {

    CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        cal=findViewById(R.id.calendarView);

    }
}
