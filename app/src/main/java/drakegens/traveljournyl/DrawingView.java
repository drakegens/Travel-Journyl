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

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        //x and y axes
        float x = 75;
        float y = 25;
        canvas.drawLine(50, 50, 750, 50, paint);
        canvas.drawLine(50, 50, 50, 1000, paint);
        canvas.drawLine(100, 100, 150, 100, paint);
        canvas.drawLine(150, 100, 600, 800, paint);
      //  paint.setStrokeWidth(500);
        paint.setTextSize(24);
        canvas.drawText("Enthusiasm Vs. Age", x, y, paint);
        x = 75;
        y = 1100;
        canvas.drawText("Your enthusiasm for travel severely decreases as you age!", x, y, paint);


    }

}