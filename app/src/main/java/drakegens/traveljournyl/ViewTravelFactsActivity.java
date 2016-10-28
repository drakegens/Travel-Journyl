package drakegens.traveljournyl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTravelFactsActivity extends AppCompatActivity {

    private Button newRandomFactbtn;
    private Button addNewTravelFactbtn;
    private TextView displayTravelFact;
    private final Context context = this;
    private String alertDialogTitle = "Add New Fact";
    private String alertDialogMessage = "Enter your fact: ";
    private EditText alertDialogText;
    private String addedNewFact;

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
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                alertDialogBuilder.setTitle(alertDialogTitle);
//                alertDialogBuilder.setMessage(alertDialogMessage);
//                alertDialogBuilder.setView(alertDialogText);
//                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                         addedNewFact = alertDialogText.getText().toString();
//                        addNewFact(addedNewFact);
//
//                    }
//                });
//                AlertDialog alert = alertDialogBuilder.create();
//                alert.show();




            }
        });


    }

    private String generateRandomFact() {
        TravelFactsDatabaseManager dbMgr = new TravelFactsDatabaseManager(this, "travel_app_db.db", null, 1);
        dbMgr.dbOpen();
       int size = dbMgr.determineSizeOfTable();

       return dbMgr.getRandomFact(size);
       // return "";
    }

    private void addNewFact(String addedNewFact) {
        TravelFactsDatabaseManager dbMgr = new TravelFactsDatabaseManager(this, "travel_app_db.db", null, 1);
        dbMgr.dbOpen();
        dbMgr.addNewFact(addedNewFact);

    }


}
