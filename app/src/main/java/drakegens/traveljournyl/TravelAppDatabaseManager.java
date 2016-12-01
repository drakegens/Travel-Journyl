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
 * Drake Gens
 * This class handles database activities used in the application, specifically for the travel facts and the travel experiences stored by the user.
 */

class TravelAppDatabaseManager extends SQLiteOpenHelper {

    private static String dbPath = "";
    private static final String dbName = "travel_app_db.db";
    private static final String tblTravelFacts = "travel_facts";
    private static final String tblTravelExperiences = "experience_information";
    private static final String colFact = "fact";
    private static final String colLocation = "location";
    private static final String colFromDate = "from_date";
    private static final String colToDate = "to_date";
    private static final String colDetails = "details";

    private final Context appContext;

    public TravelAppDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
        //not going to change versions in this app
    }

    /*
    This method checks if the database exists.
     */
    private boolean dbExist() {
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.close();
        } catch (Exception e) {
            copyDBFromAssets();
        }
        if (checkDBForData()) {
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
            Log.d("Debug", "Database opened");
            OutputStream dbOutput = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
                Log.d("Debug", "Database copying...");
            }
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e) {
            Log.e("IOException", "Problems copying database");
        }
    }

    /*
    This method determines the size of the table "fact" so the database can be queried for a random fact.
     */
    public int determineSizeOfTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.setLocale(Locale.getDefault());
        int size = (int) DatabaseUtils.queryNumEntries(db, tblTravelFacts);
        db.close();
        return size;
    }

    /*
    This method checks to see if the database is empty.
     */
    private boolean checkDBForData() {
        boolean tableExists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(tblTravelFacts, null,
                    null, null, null, null, null);
            tableExists = true;
            db.close();

        } catch (Exception e) {
            Log.d("Debug", "table doesn't exist");
        }
        return tableExists;
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
        //special container used for entering database values
        ContentValues insertValue = new ContentValues();
        insertValue.put(colFact, newFact);
        db.insert(tblTravelFacts, null, insertValue);
        db.close();
    }

    /*
    This method creates a ContentValues object for insertion of a new travel experience into the database.
     */
    public void addNewExperience(String location, String fromDate, String toDate, String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colLocation, location);
        contentValues.put(colFromDate, fromDate);
        contentValues.put(colToDate, toDate);
        contentValues.put(colDetails, details);
        db.insert(tblTravelExperiences, null, contentValues);
        db.close();
    }

    /*
    This method deletes a travel experience from the database using the specified primary key
     */

    public void deleteExperience(String id) {
        String[] idArray = {id};
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "pk=?";
        db.delete(tblTravelExperiences, whereClause, idArray);
        db.close();
    }


    /*
    This method creates a cursor used for the custom cursor adaptor used in the ListView for displaying the existing travel experiences.
     */
    public Cursor createCursorForAdapter() {
        String query = "SELECT " + "pk AS _id," + colLocation + "," + colFromDate + "," + colToDate + "," + colDetails + " FROM " + tblTravelExperiences;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }
}
