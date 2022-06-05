package com.and.codingcommunity;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "siswasmk2yk.db";

    public static final String SISWA_TABLE_NAME = "siswa";

    public static final String SISWA_COLUMN_ID = "id";
    public static final String SISWA_COLUMN_NAME = "name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table siswa " +
                        "(id integer primary key, " +
                        "name text) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS siswa");
        onCreate(sqLiteDatabase);
    }

    public void insertSiswa(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        db.insert("siswa", null, contentValues);
    }

    public ArrayList<Siswa> getAllSiswa() {

        ArrayList<Siswa> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from siswa", null);

        res.moveToFirst();
        while (res.isAfterLast() == false) {

            Siswa siswa = new Siswa(
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_ID)),
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_NAME)));

            array_list.add(siswa);

            res.moveToNext();
        }

        return array_list;
    }

    public boolean updateData(String id, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nama);
        String query = "UPDATE " + SISWA_TABLE_NAME + " SET " + SISWA_COLUMN_NAME + " = '" + nama + "' WHERE " + SISWA_COLUMN_ID + " = '" + id + "'";
        Log.d(TAG, "Query =" + query);
        db.execSQL(query);

        return true;
    }

}



