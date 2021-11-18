package com.talat.i170213_i170208;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MessagesDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Message.db";
    public static final int DB_VER = 1;
    Context context;

    public MessagesDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE MESSAGES (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, message TEXT, time TEXT)";
        sqLiteDatabase.execSQL(create);

        insertData("Jason Doe","This is a sample message.", "13:00", sqLiteDatabase);
        insertData("Jason Doe","This is a sample message.", "13:02", sqLiteDatabase);
        insertData("Samantha Williams","This is a sample message.", "Mon", sqLiteDatabase);
        insertData("Samantha Williams","This is a sample message.", "Tue", sqLiteDatabase);
        insertData("John Green","This is a sample message.", "17:34", sqLiteDatabase);
        insertData("John Green","This is a sample message.", "now", sqLiteDatabase);
    }

    public Boolean insertData(String username, String message, String time, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("message", message);
        values.put("time", time);

        double result = database.insert("MESSAGES", null, values);
        return result != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
