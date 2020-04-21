package com.example.chetan.yoga.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chetan.yoga.Interface.ItemClickListener;
import com.example.chetan.yoga.R;
import com.example.chetan.yoga.activity.ExerciseDetails;
import com.example.chetan.yoga.activity.ExerciseInformationActivity;
import com.example.chetan.yoga.model.AllExerciseData;

import java.util.ArrayList;

/**
 * Created by Chetan on 9/21/2017.
 */

public class AllExercieAdapter extends RecyclerView.Adapter<AllExercieAdapter.MyViewHolder> {

    private ArrayList<AllExerciseData> data;
    private Context ctx;

    public AllExercieAdapter(Context ctx,ArrayList<AllExerciseData> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public AllExercieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.custom_allexercises_layout,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AllExercieAdapter.MyViewHolder holder, final int position) {
       AllExerciseData dataprovider=data.get(position);
        holder.pictures.setImageDrawable(dataprovider.getPictures());
        holder.title.setText(dataprovider.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ExerciseInformationActivity.class);
                intent.putExtra(ExerciseDetails.POSITION,position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pictures;
        TextView title;
        private ItemClickListener listener;


        public MyViewHolder(View itemView) {
            super(itemView);
            pictures=(ImageView)itemView.findViewById(R.id.pictures);
            title=(TextView)itemView.findViewById(R.id.title);

        }


    }
}
