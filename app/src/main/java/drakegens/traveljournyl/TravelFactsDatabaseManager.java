package drakegens.traveljournyl;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
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
    private static final String colFact = "fact";


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
            db.close();
            return true;

        } else {
            return false;
        }
    }

    /*
    This method checks to see if the database needs copied from the assets folder.
     */
    public void dbOpen() {
        //check to see if the database has been copied from the assets folder
        if (!dbExist()) {
            copyDBFromAssets();
        }
    }

    /*
    This database is copied from the assets folder if needed.
     */
    private void copyDBFromAssets() {
        //Some of this code is adapted from Lab 5
        try {
            InputStream dbInput = appContext.getAssets().open(dbName);
            Log.d("Debug", "database opened");
            OutputStream dbOutput = new FileOutputStream(dbPath);

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

    /*
    This method determines the size of the table "fact" so the database can be queried for a random fact.
     */
    public int determineSizeOfTable() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.setLocale(Locale.getDefault());
        int size = (int) DatabaseUtils.queryNumEntries(db, tblTravelFacts);
        String strCount = Integer.toString(size);
        Log.d("Debug", strCount);
        db.close();
        return size;
    }

    /*
    This method pulls a random fact from the database to display to the user.
     */
    public String getRandomFact(int size) {
        int minimum = 1;
        int randomNum = minimum + (int) (Math.random() * size);
        String query = "SELECT fact FROM " + tblTravelFacts + " WHERE pk = " + randomNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String randomFact = cursor.getString(cursor.getColumnIndex(colFact));
        cursor.close();
        db.close();

        return randomFact;

    }

    /*
    This method creates a ContentValues object for insertion of facts into the existing database.
     */
    public void addNewFact(String newFact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues insertValue = new ContentValues();
        insertValue.put(colFact, newFact);
        db.insert(tblTravelFacts, null, insertValue);
        db.close();
    }
}
