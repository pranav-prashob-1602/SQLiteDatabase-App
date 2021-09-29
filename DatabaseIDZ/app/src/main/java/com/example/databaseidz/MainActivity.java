package com.example.databaseidz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseHelper myDB;
    ArrayList<String> studId, name, marks;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        myDB = new DatabaseHelper(MainActivity.this);
        studId = new ArrayList<>();
        name = new ArrayList<>();
        marks = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, AddActivity.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this,this, studId, name, marks);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                studId.add(cursor.getString(0));
                name.add(cursor.getString(1));
                marks.add(cursor.getString(2));
            }
        }
    }
}