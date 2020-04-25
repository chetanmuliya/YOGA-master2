package com.example.chetan.yoga.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chetan.yoga.R;

public class ExerciseInformationActivity extends AppCompatActivity {

    public static String POSITION="position";
    private int pos ;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_information);
        
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Exercises");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ImageView exercise_img = findViewById(R.id.img_exercise);
        TextView exercise_desc = findViewById(R.id.exercise_description);
        TextView exercise_benefits_posename = findViewById(R.id.benefit_pose);
        TextView exercise_benefits = findViewById(R.id.exercise_benefits);

        pos=getIntent().getIntExtra(POSITION,0);
        if(pos==0){
            toolbar.setTitle("Boat Pose");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.boatpose_desc);
            String ben_desc = getResources().getString(R.string.boatpose_benefits);
            exercise_benefits_posename.setText("Benefits of Boat Pose");
            exercise_benefits.setText(ben_desc);
            exercise_desc.setText(desc);
            exercise_img.setImageResource(R.drawable.boat_pose);
        }
        if(pos==1){
            toolbar.setTitle("Bow Pose");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.bowpose_desc);
            String ben_desc = getResources().getString(R.string.bow_pose_benefits);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Bow Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.bow_pose);
        }
        if(pos==2){
            toolbar.setTitle("Cobra Pose");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.cobrapose_desc);
            String ben_desc = getResources().getString(R.string.cobrapose_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Cobra Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.cobra_pose);
        }
        if(pos==3){
            toolbar.setTitle("Crescent Lunge");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.cresentpose_desc);
            String ben_desc = getResources().getString(R.string.cresentpose_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Crescent Lunge Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.crescent_lunge);
        }
        if(pos==4){
            toolbar.setTitle("Downward Facing Dog");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.downward_facing_dog_desc);
            String ben_desc = getResources().getString(R.string.downward_facing_dog_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Downward Facing Dog Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.downward_facing_dog);
        }
        if(pos==5){
            toolbar.setTitle("Easy Pose");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.easy_pose_desc);
            String ben_desc = getResources().getString(R.string.easy_pose_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Easy Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.easy_pose);
        }
        if(pos==6){
            toolbar.setTitle("Half Pigeon");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.half_pigeon_desc);
            String ben_desc = getResources().getString(R.string.half_pigeon_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Half Pigeon Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.half_pigeon);
        }
        if(pos==7){
            toolbar.setTitle("Low Lunge");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.lowlunge_desc);
            String ben_desc = getResources().getString(R.string.lowlunge_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Low Lunge");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.low_lunge);
        }
        if(pos==8){
            toolbar.setTitle("Upward Bow");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.upward_bow_desc);
            String ben_desc = getResources().getString(R.string.upward_bow_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Upward Pose");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.upward_bow);
        }
        if(pos==9){
            toolbar.setTitle("Warrior Pose");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.warrior_pose_desc);
            String ben_desc = getResources().getString(R.string.warrior_pose_desc);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Warrior Pose I");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.warrior_pose);
        }
        if(pos==10){
            toolbar.setTitle("Warrior Pose 2");
            setSupportActionBar(toolbar);
            String desc = getResources().getString(R.string.warrior_pose2_desc);
            String ben_desc = getResources().getString(R.string.warrior_pose2_ben);
            exercise_desc.setText(desc);
            exercise_benefits_posename.setText("Benefits of Warrior Pose II");
            exercise_benefits.setText(ben_desc);
            exercise_img.setImageResource(R.drawable.warrior_pose_2);
        }
    }

    public void onFabStart(View v) {
        Intent intent=new Intent(v.getContext(), ExerciseDetails.class);
        intent.putExtra(ExerciseDetails.POSITION,pos);
        v.getContext().startActivity(intent);
    }
}
