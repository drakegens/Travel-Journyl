package drakegens.traveljournyl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by Drake on 11/3/2016.
 */

public class CanvasView extends SurfaceView {

    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(25);
        bitmap = Bitmap.createBitmap(400, 600, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        draw(canvas);
    }


   // @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        //super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 100;
        // Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }
}
