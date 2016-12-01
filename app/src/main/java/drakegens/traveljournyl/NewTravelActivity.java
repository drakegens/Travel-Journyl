package drakegens.traveljournyl;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Drake Gens
 * This activity class handles user input for a new travel experience, and then stores it in the database.
 */
public class NewTravelActivity extends AppCompatActivity {

    private EditText location;
    private EditText fromDate;
    private EditText toDate;
    private EditText details;
    private Button exitWithoutSavebtn;
    private Button savebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);

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
            }
        });
        savebtn = (Button) findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExperience();
            }
        });
    }

    /*
    Listeners for date set dialogs.
     */
    class FromDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            fromDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(monthOfYear + 1).append("/").append(dayOfMonth).append("/")
                    .append(year).append(" "));
        }
    }

    class ToDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            toDate.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(monthOfYear + 1).append("/").append(dayOfMonth).append("/")
                    .append(year).append(" "));
        }
    }

    /*
    This method determines whether ALL editTexts have been filled in.
     */
    private boolean isFilledIn() {
        if (isEditTextFilledIn(location) && isEditTextFilledIn(fromDate) && isEditTextFilledIn(toDate) && isEditTextFilledIn(details)) {
            return true;
        } else {

            return false;
        }
    }

    /*
    This method checks whether the user has entered text into a field.
     */
    private boolean isEditTextFilledIn(EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }

    /*
    This method adds a new travel experience to the database and then displays a dialog when finished.
     */
    private void addNewExperience() {

        if (isFilledIn()) {
            TravelAppDatabaseManager dbMgr = new TravelAppDatabaseManager(this, "travel_app_db.db", null, 1);
            dbMgr.dbOpen();
            dbMgr.addNewExperience(location.getText().toString(), fromDate.getText().toString(), toDate.getText().toString(), details.getText().toString());

            AlertDialog.Builder successAlertDialogBuilder = new AlertDialog.Builder(this);
            successAlertDialogBuilder.setTitle(R.string.alertDialogSuccessTravelAddedHeader);
            successAlertDialogBuilder.setMessage(R.string.alertDialogSuccessTravelAddedMessage);
            successAlertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });

            AlertDialog alertDialog = successAlertDialogBuilder.create();
            alertDialog.show();


        } else {
            //display message prompting user to fill in all details about the travel experience
            AlertDialog.Builder failureAlertDialogBuilder = new AlertDialog.Builder(this);
            failureAlertDialogBuilder.setTitle(R.string.errorNotification);
            failureAlertDialogBuilder.setMessage(R.string.alertDialogNewTravelMessage);
            failureAlertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = failureAlertDialogBuilder.create();
            alertDialog.show();
        }
    }

}

