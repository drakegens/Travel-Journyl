package drakegens.traveljournyl;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Drake on 11/15/2016.
 */

public class CustomCursorAdapter extends ResourceCursorAdapter {
    private static final String colLocation = "location";
    private static final String colFromDate = "from_date";
    private static final String colToDate = "to_date";
    private static final String colDetails = "details";

    public CustomCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView location = (TextView) view.findViewById(R.id.location);
        location.setText(cursor.getString(cursor.getColumnIndex(colLocation)));

        TextView datesTravelled = (TextView) view.findViewById(R.id.datesTravelled);
        datesTravelled.setText(cursor.getString(cursor.getColumnIndex(colFromDate)) + " - " + cursor.getString(cursor.getColumnIndex(colToDate)));
    }
}
