package com.jeremysuh.teacuppy;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CalendarDB";
    private static final String TABLE_NAME = "Calendar";
    private static final String KEY_DATE = "date"; //id
    private static final String KEY_CUPS = "cups";
    private static final String KEY_CAFFEINE = "caffeine";
    private static final String KEY_CALORIES = "calories";
    private static final String[] COLUMNS = {KEY_DATE, KEY_CUPS, KEY_CAFFEINE,
            KEY_CALORIES};

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Calendar ( "
                + "date TEXT PRIMARY KEY, " + "cups INTEGER, "
                + "caffeine INTEGER, " + "calories INTEGER )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public int addDayStats(Date date) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date.get_date());
        values.put(KEY_CUPS, date.get_cups());
        values.put(KEY_CAFFEINE, date.get_caffeine());
        values.put(KEY_CALORIES, date.get_calories());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();

        return 0;
    }


    public int updatePlayer(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date.get_date());
        values.put(KEY_CUPS, date.get_cups());
        values.put(KEY_CAFFEINE, date.get_caffeine());
        values.put(KEY_CALORIES, date.get_calories());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "date = ?", // selections
                new String[] { String.valueOf(date.get_date()) });

        db.close();

        return i;
    }

    public List<Date> get_allDates() {

        List<Date> dates = new LinkedList<Date>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Date date = null;

        if (cursor.moveToFirst()) {
            do {
                date = new Date();
                date.set_date(cursor.getString(0));
                date.set_cups(Integer.parseInt(cursor.getString(1)));
                date.set_caffeine(Integer.parseInt(cursor.getString(2)));
                date.set_calories(Integer.parseInt(cursor.getString(3)));
                dates.add(date);
            } while (cursor.moveToNext());
        }


        return dates;
    }




}