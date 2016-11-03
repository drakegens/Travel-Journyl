package drakegens.traveljournyl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DrawingActivity extends AppCompatActivity {
    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        canvasView = (CanvasView) findViewById(R.id.canvas);
    }
}
