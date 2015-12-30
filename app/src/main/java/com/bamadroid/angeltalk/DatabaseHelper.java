package com.bamadroid.angeltalk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

/**
 * Created by ray on 12/4/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = DatabaseConstants.DATABASE_VERSION;

    // Database Name
    private static final String DATABASE_NAME = DatabaseConstants.DATABASE_NAME;

    // Table Names
    private static final String DB_TABLE = DatabaseConstants.DB_TABLE;

    // column names
    private static final String KEY_NAME = DatabaseConstants.KEY_NAME;
    private static final String KEY_IMAGE = DatabaseConstants.KEY_IMAGE;
    private static final String KEY_SOUND_PATH = DatabaseConstants.KEY_SOUND_PATH;

    public DbBitmapUtility dbBitmapUtility;

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "("+
            KEY_NAME + " TEXT," +
            KEY_IMAGE + " BLOB," +
            KEY_SOUND_PATH + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbBitmapUtility = new DbBitmapUtility(48,48);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        // create new table
        onCreate(db);
    }

    public void addEntry(SQLiteDatabase db, String aName, Bitmap aImage, String aSoundPath) throws SQLiteException
    {
        // compress and convert to Bytes
        byte[] image = dbBitmapUtility.getBytes(aImage);

        ContentValues cv = new ContentValues();
        cv.put(DatabaseConstants.KEY_NAME,    aName);
        cv.put(DatabaseConstants.KEY_IMAGE, image);
        cv.put(DatabaseConstants.KEY_SOUND_PATH, aSoundPath);
        db.insert(DatabaseConstants.DATABASE_NAME, null, cv);
    }

    public Cursor getAllItems(SQLiteDatabase db)
    {
        Cursor cursor = db.query(DB_TABLE, new String[] {
                        KEY_NAME,
                        KEY_IMAGE,
                        KEY_SOUND_PATH
                },
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

}
