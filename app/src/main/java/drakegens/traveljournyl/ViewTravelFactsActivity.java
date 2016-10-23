package drakegens.traveljournyl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTravelFactsActivity extends AppCompatActivity {

    Button newRandomFactbtn;
    Button addNewTravelFactbtn;
    TextView displayTravelFact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travel_facts);
//add error handling
        //open database tasks
        //File databaseFile = getDatabasePath(dbName);
        //dbManager.openDatabase(databaseFile, cursorFactory, OPEN_READWRITE);
        // File dbFile = this.getDatabasePath("travel_app_db");


//        dbManager.setLocale(Locale.getDefault());
//        dbManager.setVersion(version);


        displayTravelFact = (TextView) findViewById(R.id.displayFact);
        //set random fact from db
        displayTravelFact.setText(generateRandomFact());
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


    }

    private String generateRandomFact() {
        determineSizeOfTable();
        return "random fact";
    }

    private int determineSizeOfTable() {
        String query = "SELECT count(*) FROM travel_facts;";
        //SELECT count(*) FROM travel_facts;
        // Cursor cursor = dbManager.rawQuery(query, null);
        //Log.d("Debug", String.valueOf(cursor.getCount()));
        // return cursor.getCount();
        return 1;
    }
}
