package com.example.a40_day09_dohaidang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Contact.ContactPhone;

public class SQL extends SQLiteOpenHelper {
    private static final String TAG = "SQL";
    private static final String DB_NAME = "DanhBaDT.db";
    private static final String DB_TABLE = "DanhBa";
    private static final int DB_VERSION = 13;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQL(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "Create table DanhBa ("+
                "name Text,"+
                "phone Text" +")";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {
        if(OldVersion != NewVersion){
            db.execSQL("Drop Table if exists " + DB_TABLE);
            onCreate(db);
        }
    }

    public void insertData(ContactPhone CP){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name", CP.getName());
        contentValues.put("phone", CP.getPhoneNumber());

        sqLiteDatabase.insert(DB_TABLE, null, contentValues);
    }

    public void updateData(ContactPhone CP,String phoneNumber){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name", CP.getName());
        contentValues.put("phone", CP.getPhoneNumber());
        sqLiteDatabase.update(DB_TABLE,contentValues,"phone=?", new String[] {phoneNumber});
    }

    public int deleteData(String phoneNumber){
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_TABLE, "phone=?", new String[] {phoneNumber});
    }

    public ArrayList<ContactPhone> getData(){
        ArrayList<ContactPhone> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        cursor =sqLiteDatabase.query(false, DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            String Name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            list.add(new ContactPhone(Name,phone));
        }
        return list;
    }
}
