package drakegens.traveljournyl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;

public class NewTravel extends AppCompatActivity {

    TextView header;
    EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);

        header = (TextView) findViewById(R.id.header);
        location = (EditText) findViewById(R.id.location);
    }
}
