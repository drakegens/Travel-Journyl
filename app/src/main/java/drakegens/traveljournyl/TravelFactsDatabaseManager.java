package drakegens.traveljournyl;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by Drake on 10/23/2016.
 */

public class TravelFactsDatabaseManager extends SQLiteOpenHelper {

    private static String dbPath = "";
    private static final String dbName = "travel_app_db.db";
    private static final String tblTravelFacts = "travel_facts";


    private final Context appContext;

    public TravelFactsDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
        dbPath = appContext.getDatabasePath(dbName).getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//database already exists
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//who cares for now
    }

    private boolean dbExist() {
        SQLiteDatabase db;
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        if (db != null) {
            return true;
        }
        return false;
    }

    public void dbOpen() {
        SQLiteDatabase db;
        if (!dbExist()) {
            db = this.getReadableDatabase();
            copyDBFromAssets();
        } else {
            try {
                db = SQLiteDatabase.openDatabase(dbPath + dbName, null, SQLiteDatabase.OPEN_READWRITE);
                db.setLocale(Locale.getDefault());
                db.setVersion(1);
            } catch (SQLiteException e) {
                Log.e("SQLite Helper", "Database not found");
            }
        }
    }

    private void copyDBFromAssets() {
        //from Lab 5
        InputStream dbInput = null;
        OutputStream dbOutput = null;
        try {
            dbInput = appContext.getAssets().open(dbName);
            Log.d("Debug", "database opened");
            dbOutput = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
                Log.d("Debug", "database copying...");
            }
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e) {
            Log.e("IOException", "Problems copying DB");
        }
    }

    public int determineSizeOfTable() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.setLocale(Locale.getDefault());
        int size = (int) DatabaseUtils.queryNumEntries(db, "travel_facts");
        String strCount = Integer.toString(size);
        Log.d("Debug", strCount);
        return size;
    }

    public String getRandomFact(int size) {
        int minimum = 1;
        int randomNum = minimum + (int) (Math.random() * size);
        String query = "SELECT fact FROM " + tblTravelFacts + " WHERE pk = " + randomNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String randomFact = cursor.getString(cursor.getColumnIndex("fact"));
        cursor.close();
        db.close();

        return randomFact;

    }

    public void addNewFact(String newFact) {
String query = "INSERT INTO " + tblTravelFacts + "(fact) VALUES (" + newFact + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();
    }
}
