package drakegens.traveljournyl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTravelFactsActivity extends AppCompatActivity {

    Button newRandomFactbtn;
    Button addNewTravelFactbtn;
    TextView displayTravelFact;
    SQLiteOpenHelper dbManager;
    private static final String dbName = "C:\\Users\\Drake\\OneDrive\\Documents\\TravelJournyl\\app\\src\\main\\assets\\travel_app_db.db";
    private static int version = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travel_facts);

        displayTravelFact = (TextView) findViewById(R.id.displayFact);
        //set random fact from db

        newRandomFactbtn = (Button) findViewById(R.id.newRandomFactbtn);
        newRandomFactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Random fact


            }
        });
        addNewTravelFactbtn = (Button) findViewById(R.id.addNewFactbtn);
        addNewTravelFactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add fact to db


            }
        });

        dbManager = new SQLiteOpenHelper(this, dbName, null, version) {
            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };



}
}
