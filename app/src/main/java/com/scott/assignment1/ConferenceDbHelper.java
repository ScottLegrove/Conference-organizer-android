package com.scott.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.scott.assignment1.ConferenceContract.Presenters;
/**
 * Created by Scott on 18/11/2015.
 */
public class ConferenceDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "conf.db";
    private static final int DATABASE_VERSION = 14;

    public ConferenceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating database: " + DATABASE_NAME + " version: " + DATABASE_VERSION);
        db.execSQL(Presenters.CREATE);
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading database: " + DATABASE_NAME + " from version: " + oldVersion + " to " + newVersion );
        // BAD (for testing ONLY)
        db.execSQL(Presenters.DROP);
        db.execSQL(Presenters.CREATE);
        initialize(db);
        // GOOD
        /*switch (oldVersion) {
            case 1:
                // Upgrade script from version 2
                break;
        }*/
    }

    public Cursor getAllPresenters() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {Presenters._ID, Presenters.COLUMN_NAME_NAME, Presenters.COLUMN_NAME_BIO, Presenters.COLUMN_NAME_EMAIL, Presenters.COLUMN_NAME_AFFILIATION };
        return db.query(Presenters.TABLE_NAME, projection, null, null, null, null, null);
    }

    public Cursor getPresenter(int id) {
        SQLiteDatabase db = getReadableDatabase();

        // Columns to return
        String[] projection = {Presenters._ID, Presenters.COLUMN_NAME_NAME, Presenters.COLUMN_NAME_BIO, Presenters.COLUMN_NAME_EMAIL, Presenters.COLUMN_NAME_AFFILIATION };
        return db.query(Presenters.TABLE_NAME, projection, "_ID=?", new String[]{""+id}, null, null, null);
    }

    public Cursor getPresenterByName(String name) {
        SQLiteDatabase db = getReadableDatabase();


        String[] projection = {Presenters.COLUMN_NAME_NAME, Presenters.COLUMN_NAME_BIO, Presenters.COLUMN_NAME_EMAIL, Presenters.COLUMN_NAME_AFFILIATION };
        return db.query(Presenters.TABLE_NAME, projection, Presenters.COLUMN_NAME_NAME+"=?", new String[] {name}, null, null, null);
    }

    public void initialize(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Presenters.COLUMN_NAME_NAME, "Janet Burkin");
        values.put(Presenters.COLUMN_NAME_BIO, "Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text " +
                "ever since the 1500s, when an unknown printer took a " +
                "galley of type and scrambled it to make a type specimen book. It has survived no");
        values.put(Presenters.COLUMN_NAME_EMAIL, "jburkin@gmail.com");
        values.put(Presenters.COLUMN_NAME_AFFILIATION, "Oracle");

        db.insert(Presenters.TABLE_NAME, null, values);

        values.put(Presenters.COLUMN_NAME_NAME, "Masatoshi Windle");
        values.put(Presenters.COLUMN_NAME_BIO, "Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text " +
                "ever since the 1500s, when an unknown printer took a " +
                "galley of type and scrambled it to make a type specimen book. It has survived no");
        values.put(Presenters.COLUMN_NAME_EMAIL, "mwindle@gmail.com");
        values.put(Presenters.COLUMN_NAME_AFFILIATION, "Mozilla");

        db.insert(Presenters.TABLE_NAME, null, values);

        values.put(Presenters.COLUMN_NAME_NAME, "Barack Obama");
        values.put(Presenters.COLUMN_NAME_BIO, "Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text " +
                "ever since the 1500s, when an unknown printer took a " +
                "galley of type and scrambled it to make a type specimen book. It has survived no");
        values.put(Presenters.COLUMN_NAME_EMAIL, "bobama@gmail.com");
        values.put(Presenters.COLUMN_NAME_AFFILIATION, "Google");

        db.insert(Presenters.TABLE_NAME, null, values);

        values.put(Presenters.COLUMN_NAME_NAME, "James Bond");
        values.put(Presenters.COLUMN_NAME_BIO, "Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text " +
                "ever since the 1500s, when an unknown printer took a " +
                "galley of type and scrambled it to make a type specimen book. It has survived no");
        values.put(Presenters.COLUMN_NAME_EMAIL, "jbond@gmail.com");
        values.put(Presenters.COLUMN_NAME_AFFILIATION, "Microsoft");

        db.insert(Presenters.TABLE_NAME, null, values);

    }

    public void addPresenter(String name, String email, String bio, String affiliation) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Presenters.COLUMN_NAME_NAME, name);
        values.put(Presenters.COLUMN_NAME_BIO, bio);
        values.put(Presenters.COLUMN_NAME_EMAIL, email);
        values.put(Presenters.COLUMN_NAME_AFFILIATION, affiliation);

        long id = db.insert(Presenters.TABLE_NAME, null, values);
        System.out.println("Item " + id + " added successfully");
    }

}
