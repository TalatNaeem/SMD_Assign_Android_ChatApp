package com.talat.i170213_i170208;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ChatDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Contact.db";
    public static final int DB_VER = 1;
    Context context;

    public ChatDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE CONTACTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number TEXT, image INTEGER, last_message TEXT, last_active TEXT)";
        sqLiteDatabase.execSQL(create);

        insertData("Jason Doe", "(111) 1234", R.drawable.logo, "Sure, I'll come.", "Mon", sqLiteDatabase);
        insertData("Samantha Williams", "(222) 5678", R.drawable.logo, "No Problem.", "now", sqLiteDatabase);
        insertData("John Green", "(333) 1956", R.drawable.logo, "Should I come earlier.", "13:00", sqLiteDatabase);

    }

    public Boolean insertData(String name, String number, Integer image, String last_message, String last_active, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("number", number);
        values.put("image", image);
        values.put("last_message", last_message);
        values.put("last_active", last_active);

        double result = database.insert("CONTACTS", null, values);
        return result != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
