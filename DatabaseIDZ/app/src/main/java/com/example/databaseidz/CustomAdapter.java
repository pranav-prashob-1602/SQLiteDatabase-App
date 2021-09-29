package com.example.databaseidz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewholder> {

    private Context context;
    Activity activity;
    private ArrayList id, name, marks;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList name, ArrayList marks) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.marks = marks;

    }


    @NonNull
    @Override
    public CustomAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewholder holder, int position) {
        holder.studId.setText(String.valueOf(id.get(position)));
        holder.studName.setText(String.valueOf(name.get(position)));
        holder.studMarks.setText(String.valueOf(marks.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("marks", String.valueOf(marks.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView studName, studMarks, studId;
        LinearLayout mainLayout;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            studId = itemView.findViewById(R.id.studId);
            studName = itemView.findViewById(R.id.studName);
            studMarks = itemView.findViewById(R.id.studMarks);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
