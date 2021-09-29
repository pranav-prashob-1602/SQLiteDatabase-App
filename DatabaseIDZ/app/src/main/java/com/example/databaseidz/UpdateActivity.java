package com.example.databaseidz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameInput2, marksInput2;
    Button updateButton, deleteButton;

    String id, name, marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameInput2 = findViewById(R.id.nameInput2);
        marksInput2 = findViewById(R.id.marksInput2);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            ab.setTitle(name);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name=nameInput2.getText().toString().trim();
                marks=marksInput2.getText().toString().trim();
                myDB.updateData(id, name, Integer.valueOf(marks));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteData(id);
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("marks")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            marks = getIntent().getStringExtra("marks");

            nameInput2.setText(name);
            marksInput2.setText(marks);
        }else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

}