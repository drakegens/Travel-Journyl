package drakegens.traveljournyl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //instance variables
    Button newTravelExperience;
    Button existingTravelExperience;
    Button viewTravelFacts;
    Button viewRSSFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainMenuToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainMenuToolbar);
        //Wiring up my buttons to go to correct activity onClick
        newTravelExperience = (Button) findViewById(R.id.newTravelExbtn);
        newTravelExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch that activity
                Intent intent = new Intent(MainActivity.this, NewTravelActivity.class);
                startActivity(intent);

            }


        });
        existingTravelExperience = (Button) findViewById(R.id.existTravelExbtn);
        existingTravelExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch that activity
            }


        });
        viewTravelFacts = (Button) findViewById(R.id.travelFactsbtn);
        viewTravelFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch that activity
                Intent intent = new Intent(MainActivity.this, ViewTravelFactsActivity.class);
                startActivity(intent);
            }


        });
        viewRSSFeed = (Button) findViewById(R.id.RSSFeedbtn);
        viewRSSFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch that activity
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_button_items, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                //create the about dialog and display it, informing users as to what the application does
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle(R.string.alertDialogAboutTitle);
                alertDialogBuilder.setMessage(R.string.alertDialogAboutMessage);
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
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
