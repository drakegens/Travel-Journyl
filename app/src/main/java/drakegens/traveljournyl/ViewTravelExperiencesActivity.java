package drakegens.traveljournyl;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
/**
This class creates the database manager and sets the list view for this activity.
 Drake Gens
 */
public class ViewTravelExperiencesActivity extends AppCompatActivity {

    private ListView existingTravelExperiencesList;
    private CustomCursorAdapter cursorAdapter;
    private Cursor cursor;
    private static final String colLocation = "location";
    private static final String colFromDate = "from_date";
    private static final String colToDate = "to_date";
    private static final String colDetails = "details";
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travel_experiences);
        existingTravelExperiencesList = (ListView) findViewById(R.id.existingTravelExperiencesList);

        TravelAppDatabaseManager dbMgr = new TravelAppDatabaseManager(this, "travel_app_db.db", null, 1);
        dbMgr.dbOpen();

        cursor = dbMgr.createCursorForAdapter();

        cursorAdapter = new CustomCursorAdapter(this, R.layout.row, cursor, 0);

        existingTravelExperiencesList.setAdapter(cursorAdapter);


        existingTravelExperiencesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //build an alert dialog so that the details of the travel experience can be viewed
                cursor.moveToPosition(position);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(cursor.getString(cursor.getColumnIndex(colLocation)) + "\n" + cursor.getString(cursor.getColumnIndex(colFromDate)) + " - " + cursor.getString(cursor.getColumnIndex(colToDate)));
                alertDialogBuilder.setMessage(cursor.getString(cursor.getColumnIndex(colDetails)));
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }
        });
    }
}
