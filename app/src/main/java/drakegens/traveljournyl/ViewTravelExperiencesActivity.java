package drakegens.traveljournyl;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class ViewTravelExperiencesActivity extends AppCompatActivity {

    private ListView existingTravelExperiencesList;
    private CursorAdapter cursorAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travel_experiences);
        existingTravelExperiencesList = (ListView) findViewById(R.id.existingTravelExperiencesList);

        TravelAppDatabaseManager dbMgr = new TravelAppDatabaseManager(this, "travel_app_db.db", null, 1);
        dbMgr.dbOpen();

        cursor = dbMgr.createCursorForAdaptor();

        CustomCursorAdapter cursorAdapter = new CustomCursorAdapter(this,R.layout.row, cursor, 0);

        existingTravelExperiencesList.setAdapter(cursorAdapter);

    }
}
