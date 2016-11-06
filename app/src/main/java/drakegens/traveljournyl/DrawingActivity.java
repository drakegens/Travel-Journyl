package drakegens.traveljournyl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity launched to display the canvas upon which I draw a graph.
 * Drake Gens
 */
public class DrawingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingView(this));
    }
}
