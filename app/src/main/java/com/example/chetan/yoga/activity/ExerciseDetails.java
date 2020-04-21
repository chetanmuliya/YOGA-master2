package com.example.chetan.yoga.activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.database.YogaDB;
import com.example.chetan.yoga.model.LevelSetting;

import java.util.Calendar;

import info.hoang8f.widget.FButton;

public class ExerciseDetails extends AppCompatActivity {

    ImageView pictures;
    Button startButton;
    TextView timmer;
    Toolbar toolbar;
    YogaDB yogaDB;
    Boolean isRunning=false;
    public static String POSITION="position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        pictures=(ImageView)findViewById(R.id.pictures);
        startButton=(Button) findViewById(R.id.startButton);
        timmer=(TextView) findViewById(R.id.timmer);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Exercises");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //database
        yogaDB=new YogaDB(this);

        int pos=getIntent().getIntExtra(POSITION,0);
        if(pos==0){
            toolbar.setTitle("Boat Pose");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.boat_pose);
        }
        if(pos==1){
            toolbar.setTitle("Bow Pose");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.bow_pose);
        }
        if(pos==2){
            toolbar.setTitle("Cobra Pose");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.cobra_pose);
        }
        if(pos==3){
            toolbar.setTitle("Crescent Lunge");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.crescent_lunge);
        }
        if(pos==4){
            toolbar.setTitle("Downward Facing Dog");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.downward_facing_dog);
        }
        if(pos==5){
            toolbar.setTitle("Easy Pose");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.easy_pose);
        }
        if(pos==6){
            toolbar.setTitle("Half Pigeon");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.half_pigeon);
        }
        if(pos==7){
            toolbar.setTitle("Low Lunge");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.low_lunge);
        }
        if(pos==8){
            toolbar.setTitle("Upward Bow");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.upward_bow);
        }
        if(pos==9){
            toolbar.setTitle("Warrior Pose");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.warrior_pose);
        }
        if(pos==10){
            toolbar.setTitle("Warrior Pose 2");
            setSupportActionBar(toolbar);
            pictures.setImageResource(R.drawable.warrior_pose_2);
        }
    }

    public void onStart(View view) {
        if(!isRunning) {
            startButton.setText("DONE");
            int timeLimit=0;
            if(yogaDB.getSettingMode()==1){
                timeLimit= LevelSetting.MODE_EASY;
            }
            if(yogaDB.getSettingMode()==2){
                timeLimit= LevelSetting.MODE_Medium;
            }
            if(yogaDB.getSettingMode()==3){
                timeLimit= LevelSetting.MODE_HARD;
            }
            new CountDownTimer(timeLimit, 1000) {
                @Override
              public void onTick(long l) {
                    timmer.setText("" + l / 1000);
                }

                @Override
                public void onFinish() {
                    yogaDB.saveWorkoutDays(""+Calendar.getInstance().getTimeInMillis());
                    Toast.makeText(getApplicationContext(), "Welldone You did a good job", Toast.LENGTH_LONG).show();
                    finish();
                }
            }.start();
        }else{
            Toast.makeText(getApplicationContext(), "Finish!", Toast.LENGTH_LONG).show();
        }
        isRunning=!isRunning;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setTitleColor(R.color.colorPrimary);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}