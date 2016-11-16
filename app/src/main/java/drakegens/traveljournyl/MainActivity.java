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


/**
 * Drake Gens
 * Main Activity launched when the application is initially opened. Serves as the main menu
 */
public class MainActivity extends AppCompatActivity {

    private Button newTravelExperience;
    private Button existingTravelExperience;
    private Button viewTravelFacts;
    private Button viewRSSFeed;
    private Toolbar mainMenuToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainMenuToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainMenuToolbar);

        //Wiring up my buttons to go to correct activity onClick
        newTravelExperience = (Button) findViewById(R.id.newTravelExbtn);
        newTravelExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launches new travel activity
                Intent intent = new Intent(MainActivity.this, NewTravelActivity.class);
                startActivity(intent);

            }


        });

        existingTravelExperience = (Button) findViewById(R.id.existTravelExbtn);
        existingTravelExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch View Travel experiences activity
                Intent intent = new Intent(MainActivity.this, ViewTravelExperiencesActivity.class);
                startActivity(intent);
            }


        });

        viewTravelFacts = (Button) findViewById(R.id.travelFactsbtn);
        viewTravelFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launches travel facts activity
                Intent intent = new Intent(MainActivity.this, ViewTravelFactsActivity.class);
                startActivity(intent);
            }


        });

        viewRSSFeed = (Button) findViewById(R.id.RSSFeedbtn);
        viewRSSFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launches RSS feed activity
                Intent intent = new Intent(MainActivity.this, ViewRSSFeedActivity.class);
                startActivity(intent);
            }


        });

    }

    /*
    This method adds the buttons to the toolbar at the top of the activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_button_items, menu);
        return true;
    }

    /*
    This method handles the toolbar's buttons, launching the appropriate actions depending on user interaction.
     */
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
            case R.id.action_draw:
                //The canvas is displayed and a graph is drawn on it
                Intent drawingIntent = new Intent(MainActivity.this, DrawingActivity.class);
                startActivity(drawingIntent);
                return true;
            case R.id.action_maps:

                Intent mapsIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapsIntent);

            default:
                // If we got here, the user's action was not recognized.
                return super.onOptionsItemSelected(item);

        }
    }


}
