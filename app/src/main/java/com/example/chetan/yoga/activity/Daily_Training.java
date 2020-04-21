package com.example.chetan.yoga.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.database.YogaDB;
import com.example.chetan.yoga.model.AllExerciseData;
import com.example.chetan.yoga.model.LevelSetting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.hoang8f.widget.FButton;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_Training extends AppCompatActivity {

    Toolbar toolbar;
    TextView getready,countdown,timmer;
    LinearLayout linearLayout;
    ImageView pictures;
    YogaDB yogaDB;
    MaterialProgressBar progressBar;
    boolean isRunning=false;
    Button start;
    List<AllExerciseData> exerciseDatas=new ArrayList<>();
    int ex_id=0;
    int timeLimit=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Daily Exercises");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayoutGetReady);
        getready=(TextView)findViewById(R.id.getReady);
        timmer=(TextView)findViewById(R.id.timmer);
        countdown=(TextView)findViewById(R.id.countDown);
        pictures=(ImageView)findViewById(R.id.pictures);
        progressBar=(MaterialProgressBar) findViewById(R.id.progressbar);
        start=(Button) findViewById(R.id.startButton);
        yogaDB=new YogaDB(this);


        //resources
        Resources res=getResources();
        String[] name=res.getStringArray(R.array.allExerciseName);
        Drawable[] pic;
        TypedArray a=res.obtainTypedArray(R.array.allExercisePictures);
        pic=new Drawable[a.length()];
        for (int i=0;i<pic.length;i++){
            pic[i]=a.getDrawable(i);
        }

        for(int i=0;i<name.length;i++){
            AllExerciseData data=new AllExerciseData(name[i],pic[i]);
            exerciseDatas.add(data);
        }
        setExerciseData(ex_id);
        progressBar.setMax(exerciseDatas.size());
        Log.d("*********exercisedatas", "onCreate: "+exerciseDatas.size());
        pictures.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        //timelimit
        if(yogaDB.getSettingMode()==1){
            timeLimit= LevelSetting.MODE_EASY;
        }
        if(yogaDB.getSettingMode()==2){
            timeLimit= LevelSetting.MODE_Medium;
        }
        if(yogaDB.getSettingMode()==3){
            timeLimit= LevelSetting.MODE_HARD;
        }

        new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {
                countdown.setText(""+l/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();

    }

    private void showExercises() {
        if (ex_id < exerciseDatas.size()) {
            setExerciseData(ex_id);
             progressBar.setProgress(ex_id);
            pictures.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
            exerciseCountDown.start();
            Log.d("*********exercisedatas", "showexercise: "+exerciseDatas.size());
        }
    }

    private void setExerciseData(int ex_id) {


        pictures.setImageDrawable(exerciseDatas.get(ex_id).getPictures());
       toolbar.setTitle(exerciseDatas.get(ex_id).getTitle());
        setSupportActionBar(toolbar);
    }

    public void onStartExercise(View view) {
        if(start.getText().toString().trim().equals("start")) {
            if (!isRunning) {
                start.setText("done");
                new CountDownTimer(timeLimit, 1000) {
                    @Override
                    public void onTick(long l) {
                        timmer.setText("" + l / 1000);
                    }

                    @Override
                    public void onFinish() {
                        if(ex_id<exerciseDatas.size()) {
                            restTime();
                        }
                    }
                }.start();
            } else {
                Toast.makeText(getApplicationContext(), "Finish!", Toast.LENGTH_LONG).show();
            }
        }else if(start.getText().toString().trim().equals("done")){
            pictures.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            start.setVisibility(View.INVISIBLE);
            timmer.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            getready.setText("Finished!");
            countdown.setText("");
            countdown.setTextSize(20);

        }else {

        }
    }
CountDownTimer rest;
    private void restTime() {
        pictures.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        timmer.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        getready.setText("Rest Time");
       rest= new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long l) {
                countdown.setText(""+l/1000);
            }

            @Override
            public void onFinish() {
                if (ex_id < exerciseDatas.size()) {

                    setExerciseData(ex_id);
                    ex_id++;
                    progressBar.setProgress(ex_id);
                    pictures.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    start.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                    timmer.setVisibility(View.VISIBLE);
                    exerciseCountDown.start();
                }
            }
        }.start();
    }

    CountDownTimer exerciseCountDown=new CountDownTimer(timeLimit,1000) {
        @Override
        public void onTick(long l) {
            timmer.setText(""+l/1000);
        }
        @Override
        public void onFinish() {
            if(ex_id<exerciseDatas.size()){
                Log.d("*******ex_id********", "onFinish:ex_id "+ex_id);
                Log.d("**exerciseDatas size***", "onFinish:size "+exerciseDatas.size());
                progressBar.setProgress(ex_id);
               setExerciseData(ex_id);
                timmer.setText("");
                start.setText("start");
            }else{
                pictures.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                timmer.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                getready.setText("Finished!");
                countdown.setText("Great Job! Your Done with \n Today's Workout");
                toolbar.setTitle("Great Work");
                setSupportActionBar(toolbar);
                yogaDB.saveWorkoutDays(""+Calendar.getInstance().getTimeInMillis());
                countdown.setTextSize(20);
                exerciseCountDown.cancel();
            }
        }
    };
}

