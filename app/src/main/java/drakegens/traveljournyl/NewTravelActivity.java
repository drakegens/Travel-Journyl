package drakegens.traveljournyl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);

        //header = (TextView) findViewById(R.id.header);
        location = (EditText) findViewById(R.id.location);
        fromDate = (EditText) findViewById(R.id.etFromDate);
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(NewTravelActivity.this,
                        new FromDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }

        });
    }


    class FromDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
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


}

