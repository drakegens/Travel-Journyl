import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Drake on 10/23/2016.
 */

public class TravelFactsDatabaseManager extends SQLiteOpenHelper {

    private static final String dbPath = "/data/data/drakegens.traveljournyl/databases/";
    private static final String dbName = "travel_app_db.db";

    public TravelFactsDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
