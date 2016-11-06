package drakegens.traveljournyl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Drake Gens
 * The View class holds a blank canvas, allowing for a static drawing to be created.
 */

public class DrawingView extends View {

    private Paint paint;

    public DrawingView(Context context) {
        super(context);
        // create the Paint and set its color
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLUE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(7);
        //x and y axes
        canvas.drawLine(50,50,750,50,paint);
        canvas.drawLine(50,50,50,1000,paint);
       // canvas.drawCircle(200, 200, 100, paint);
    }

}