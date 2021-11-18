package com.talat.i170213_i170208;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Chat.db";
    public static final int DB_VER = 1;
    Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE USERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(create);

        insertData("murtazahassnain17@gmail.com", "abcdef", sqLiteDatabase);
        insertData("murtazahassnain@outlook.com", "123456", sqLiteDatabase);
    }

    public Boolean insertData(String email, String password, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        double result = database.insert("USERS", null, values);
        return result != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
