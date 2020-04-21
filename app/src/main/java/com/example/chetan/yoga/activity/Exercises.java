package com.example.chetan.yoga.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chetan.yoga.Adapter.AllExercieAdapter;
import com.example.chetan.yoga.R;
import com.example.chetan.yoga.model.AllExerciseData;

import java.util.ArrayList;

public class Exercises extends AppCompatActivity {

    private String[] title;
    private Drawable[] pictures;
    ArrayList<AllExerciseData> dataArrayList;
    AllExercieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Exercises");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Resources res=getResources();
        title=res.getStringArray(R.array.allExerciseName);
        TypedArray a=res.obtainTypedArray(R.array.allExercisePictures);
        pictures=new Drawable[a.length()];
        for (int i=0;i<pictures.length;i++){
            pictures[i]=a.getDrawable(i);
        }
        dataArrayList=new ArrayList<>();
        adapter =new AllExercieAdapter(this,dataArrayList);
        for (int i=0;i<pictures.length;i++){
            AllExerciseData data=new AllExerciseData(title[i],pictures[i]);
            dataArrayList.add(data);
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
