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

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

/** Transform the content human readable. */
public class GetTaskListCursorAdapter extends CursorAdapter {

    public GetTaskListCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_list_row, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView started = (TextView) view.findViewById(R.id.started);
        long timestampInMilliseconds = cursor.getLong(cursor.getColumnIndex(Task.VALUE_STARTED));

        CharSequence format = DateFormat.format("E d.M", timestampInMilliseconds);
        started.setText(format);

        TextView category = (TextView)view.findViewById(R.id.category);
        category.setText(cursor.getString(cursor.getColumnIndex(Task.VALUE_CATEGORY)));

        TextView duration = (TextView)view.findViewById(R.id.duration);
        int durationInMilliseconds = cursor.getInt(cursor.getColumnIndex(Task.VALUE_DURATION));
        if(durationInMilliseconds == 0) {
            duration.setText("on going");
        } else {
            int durationInMinutes = durationInMilliseconds / 1000 / 60;
            duration.setText(Integer.toString(durationInMinutes == 0 ? 1 : durationInMinutes) + " min");
        }
    }
}
