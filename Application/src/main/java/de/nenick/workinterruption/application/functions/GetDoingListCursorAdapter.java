package de.nenick.workinterruption.application.functions;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.dataaccess.TimeSheetTable;

/** Transform the content human readable. */
public class GetDoingListCursorAdapter extends CursorAdapter {

    public GetDoingListCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doing_row, parent, false);
        bindView(view, context, cursor);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView began = (TextView) view.findViewById(R.id.began);
        long timestampInMilliseconds = cursor.getLong(cursor.getColumnIndex(TimeSheetTable.COL_BEGAN));

        CharSequence format = DateFormat.format("E d.M", timestampInMilliseconds);
        began.setText(format);

        TextView category = (TextView)view.findViewById(R.id.category);
        category.setText(cursor.getString(cursor.getColumnIndex(TimeSheetTable.COL_CATEGORY)));

        TextView duration = (TextView)view.findViewById(R.id.duration);
        int durationInMilliseconds = cursor.getInt(cursor.getColumnIndex(TimeSheetTable.COL_DURATION));
        duration.setText(Integer.toString(durationInMilliseconds));
    }
}
