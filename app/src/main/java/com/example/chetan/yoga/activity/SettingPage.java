package com.example.chetan.yoga.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.database.YogaDB;
import com.example.chetan.yoga.reciever.AlarmNotificationRecevier;

import java.util.Calendar;
import java.util.Date;

import info.hoang8f.widget.FButton;

public class SettingPage extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton easy, hard, medium;
    Button saveButton;
    TimePicker timepicker;
    ToggleButton switchAlarm;
    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        radioGroup = (RadioGroup) findViewById(R.id.radiogrp);
        easy = (RadioButton) findViewById(R.id.easyRadioButton);
        hard = (RadioButton) findViewById(R.id.hardRadioButton);
        medium = (RadioButton) findViewById(R.id.mediumRadioButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        switchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);
        timepicker = (TimePicker) findViewById(R.id.timePicker);
        //database
        yogaDB = new YogaDB(this);
        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkoutMode();
                Toast.makeText(getApplicationContext(), "Setting Saved", Toast.LENGTH_LONG).show();
                Intent home=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(home);
            }
        });
    }

    private void saveWorkoutMode() {
        int level = radioGroup.getCheckedRadioButtonId();
        if (level == R.id.easyRadioButton) {
            yogaDB.saveSetting(1);
        }
        if (level == R.id.mediumRadioButton) {
            yogaDB.saveSetting(2);
        }
        if (level == R.id.hardRadioButton) {
            yogaDB.saveSetting(3);
        }
    }

    public void setRadioButton(int mode) {

        if (mode == 1) {
            radioGroup.check(R.id.easyRadioButton);
        }
        if (mode == 2) {
            radioGroup.check(R.id.mediumRadioButton);
        }
        if (mode == 3) {
            radioGroup.check(R.id.hardRadioButton);
        }

    }
    public void setAlarm(View v){
        saveAlarm(switchAlarm.isChecked());
    }
    public void saveAlarm(Boolean checked) {
        if (checked) {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;
            intent = new Intent(SettingPage.this, AlarmNotificationRecevier.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(calendar.HOUR_OF_DAY,timepicker.getHour());
            calendar.set(calendar.MINUTE,timepicker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

            Toast.makeText(getApplicationContext(),"Remainder Set "+timepicker.getHour()+":"+timepicker.getMinute(),Toast.LENGTH_LONG).show();
        }else
        {
            Intent intent;
            PendingIntent pendingIntent;
            intent = new Intent(SettingPage.this, AlarmNotificationRecevier.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }
    }
}