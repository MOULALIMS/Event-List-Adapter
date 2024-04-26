package com.example.revatrail2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDatabase.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyContract.MyEntry.TABLE_NAME + " (" +
                    MyContract.MyEntry._ID + " INTEGER PRIMARY KEY," +
                    MyContract.MyEntry.COLUMN_NAME + " TEXT," +
                    MyContract.MyEntry.COLUMN_DESCRIPTION + " TEXT," +
                    MyContract.MyEntry.COLUMN_LINK + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyContract.MyEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
