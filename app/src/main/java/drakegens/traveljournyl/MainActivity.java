package drakegens.traveljournyl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


}
