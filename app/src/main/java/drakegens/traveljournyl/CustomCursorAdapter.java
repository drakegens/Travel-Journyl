package drakegens.traveljournyl;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Drake Gens
 * This class uses the cursor retrived from the database manager to add appropriate data to the ListView in the view experiences activity.
 */

public class CustomCursorAdapter extends ResourceCursorAdapter {
    private static final String colLocation = "location";
    private static final String colFromDate = "from_date";
    private static final String colToDate = "to_date";

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
