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

        displayTravelFact = (TextView) findViewById(R.id.displayFact);
        //set random fact from db
        displayTravelFact.setText(generateRandomFact());
        newRandomFactbtn = (Button) findViewById(R.id.newRandomFactbtn);
        newRandomFactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Random fact

                displayTravelFact.setText(generateRandomFact());
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
        TravelFactsDatabaseManager dbMgr = new TravelFactsDatabaseManager(this, "travel_facts_db.db", null, 1);
        dbMgr.dbOpen();
        int size = dbMgr.determineSizeOfTable();

        return dbMgr.getRandomFact(size);
    }


}
