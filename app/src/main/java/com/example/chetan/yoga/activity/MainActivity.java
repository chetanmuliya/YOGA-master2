package com.example.chetan.yoga.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.chetan.yoga.R;
import com.example.chetan.yoga.activity.Exercises;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView exercises;
    ImageView play;
    AdView mAdview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exercises=(CircleImageView)findViewById(R.id.allExercise);
        play=(ImageView)findViewById(R.id.play);

        //ad view
        mAdview=(AdView)findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdRequest request = new AdRequest.Builder()
                .addTestDevice("33BE2250B43518CCDA7DE426D04EE232")
                .build();
        mAdview.loadAd(request);

    }
    public void allExercises(View view) {
        startActivity(new Intent(this, Exercises.class));
    }

    public void onPlay(View view) {
        Intent intent=new Intent(getApplicationContext(),Daily_Training.class);
        startActivity(intent);
    }

    public void onSetting(View view) {
        Intent intent=new Intent(getApplicationContext(),SettingPage.class);
        startActivity(intent);
    }

    public void workoutDone(View view) {
        Intent intent=new Intent(getApplicationContext(),WorkoutDoneCalendar.class);
        startActivity(intent);
    }


}
