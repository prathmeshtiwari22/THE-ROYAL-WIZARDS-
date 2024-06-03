package com.example.doctorapp;
// DatabaseHelper.java


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DoctorApp.db";
    private static final String TABLE_NAME = "Appointments";
    private static final String COL_ID = "ID";
    private static final String COL_SURGERY_NAME = "SurgeryName";
    private static final String COL_SURGERY_DESCRIPTION = "SurgeryDescription";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SURGERY_NAME + " TEXT, " +
                COL_SURGERY_DESCRIPTION + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addAppointment(String surgeryName, String surgeryDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SURGERY_NAME, surgeryName);
        contentValues.put(COL_SURGERY_DESCRIPTION, surgeryDescription);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }
}
