package de.nenick.workinterruption.application.functions;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;

import de.nenick.workinterruption.application.MainActivity;
import de.nenick.workinterruption.dataaccess.TimeSheetTable;
import de.nenick.workinterruption.dataaccess.api.WorkInterruption;

/**
 * Callback handler for asynchronous loading the list of doings.
 *
 * You must first set the adapter and context before your can init this.
 */
public class GetOpenDoingsLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private MainActivity activity;

    private Context context;

    public void setCallback(MainActivity activity) {
        this.activity = activity;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {TimeSheetTable._ID, TimeSheetTable.COL_BEGAN, TimeSheetTable.COL_CATEGORY};

        CursorLoader cursorLoader = new CursorLoader(context, WorkInterruption.TimeSheet.CONTENT_URI, projection, TimeSheetTable.COL_DURATION + " = null", null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        activity.onOpenDoings(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
