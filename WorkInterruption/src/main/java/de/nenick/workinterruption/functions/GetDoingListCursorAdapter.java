package de.nenick.workinterruption.functions;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

/** Transform the content human readable. */
public class GetDoingListCursorAdapter extends SimpleCursorAdapter {

    public GetDoingListCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public CharSequence convertToString(Cursor cursor) {

        CharSequence charSequence = super.convertToString(cursor);
        return charSequence;
    }
}
