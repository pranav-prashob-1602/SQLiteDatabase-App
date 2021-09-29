package com.example.databaseidz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME="Student.db";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="student_details";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="student_name";
    private static final String COLUMN_MARKS="student_marks";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_MARKS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addStudent(String name, int marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_MARKS, marks);

        long res = db.insert(TABLE_NAME, null, cv);

        if(res==-1) {
            Toast.makeText(context, "Adding Data Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Data Added Successful", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, int marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, row_id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_MARKS, marks);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Updating Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updating Successful", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
