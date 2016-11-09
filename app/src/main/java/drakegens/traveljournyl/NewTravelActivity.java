package drakegens.traveljournyl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * This activity class handles user input for a new travel experience, and then stores it in the database.
 */
public class NewTravelActivity extends AppCompatActivity {
    //instance variables

    private EditText location;
    private EditText fromDate;
    private EditText toDate;
    private EditText details;
    private Button exitWithoutSavebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);

        //header = (TextView) findViewById(R.id.header);
        location = (EditText) findViewById(R.id.location);
        details = (EditText) findViewById(R.id.etExperienceDetails);
        fromDate = (EditText) findViewById(R.id.etFromDate);
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                // System.out.println("the selected " + day);
                DatePickerDialog dialog = new DatePickerDialog(NewTravelActivity.this,
                        new FromDateSetListener(), year, month, day);
                dialog.show();
            }

        });
        toDate = (EditText) findViewById(R.id.etToDate);
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(NewTravelActivity.this,
                        new ToDateSetListener(), year, month, day);
                dialog.show();
            }

        });


        //adding button listeners
        exitWithoutSavebtn = (Button) findViewById(R.id.exitWoutSavingbtn);
        exitWithoutSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //TODO: might have to add more code here, closing db or something
            }
        });
    }


    class FromDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // getCalender();
//            int mYear = year;
//            int mMonth = monthOfYear;
//            int mDay = dayOfMonth;
            fromDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(monthOfYear + 1).append("/").append(dayOfMonth).append("/")
                    .append(year).append(" "));
            //System.out.println(v.getText().toString());


        }
    }

    class ToDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            // getCalender();
//            int mYear = year;
//            int mMonth = monthOfYear;
//            int mDay = dayOfMonth;
            toDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(monthOfYear + 1).append("/").append(dayOfMonth).append("/")
                    .append(year).append(" "));
            //System.out.println(v.getText().toString());


        }
    }

    private boolean checkIfFilledIn() {
        if (location.getText() != null && fromDate.getText() != null && toDate.getText() != null && details.getText() != null) {
            return true;
        } else {
            return false;
        }
    }

}

