package drakegens.traveljournyl;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Locale;

/**
 * Created by Drake on 10/23/2016.
 */

public class TravelFactsDatabaseManager extends SQLiteOpenHelper {

    private static final String dbPath = "/data/data/drakegens.traveljournyl/databases/";
    private static final String dbName = "travel_app_db.db";


    private final Context appContext;

    public TravelFactsDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//database already exists
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//who cares for now
    }

    public void dbOpen() {
        SQLiteDatabase db;

        try {
            db = SQLiteDatabase.openDatabase(dbPath + dbName, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        } catch (SQLiteException e) {
            Log.e("SQLite Helper", "Database not found");
        }
    }

    public int determineSizeOfTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        int size = 0;
        String query = "SELECT count(*) FROM travel_facts";
        Cursor cursor = db.rawQuery(query, null);
        size = cursor.getInt(0);//maybe this should be 1?
        cursor.close();
        db.close();

        return size;
    }

    public String getRandomFact(int size) {
        int minimum = 1;
        int randomNum = minimum + (int) (Math.random() * size);
        String query = "SELECT fact FROM travel_facts WHERE pk = " + randomNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String randomFact = cursor.getString(0);//maybe this should be 1?
        cursor.close();
        db.close();

        return randomFact;

    }

    public void addNewFact(){

    }
}
