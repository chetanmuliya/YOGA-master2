package com.example.chetan.yoga.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.database.YogaDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class WorkoutDoneCalendar extends AppCompatActivity {

    MaterialCalendarView calendar;
    YogaDB db;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_done_calendar);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Daily Training Record");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       calendar=(MaterialCalendarView)findViewById(R.id.calendar);
        db=new YogaDB(this);

        List<String> workoutDays=db.getWorkoutdays();
        final HashSet<CalendarDay> convertedList=new HashSet<>();
        for (final String value:workoutDays){
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
            calendar.addDecorator(new DayViewDecorator() {
                @Override
                public boolean shouldDecorate(CalendarDay day) {
                    return  convertedList.contains(day);
                }
                @Override
                public void decorate(DayViewFacade view) {
                 view.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF7043")));
                }
            });
       }
    }
}
