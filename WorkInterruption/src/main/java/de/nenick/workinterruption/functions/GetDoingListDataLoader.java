package de.nenick.workinterruption.functions;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;

import de.nenick.workinterruption.contentprovider.TimeSheetTable;
import de.nenick.workinterruption.contentprovider.WorkInterruption;

/**
 * Callback handler for asynchronous loading the list of doings.
 *
 * You must first set the adapter and context before your can init this.
 */
public class GetDoingListDataLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter adapter;

    private Context context;

    public void setCursorAdapter(CursorAdapter adapter) {
        this.adapter = adapter;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {TimeSheetTable._ID, TimeSheetTable.COL_BEGAN, TimeSheetTable.COL_CATEGORY, TimeSheetTable.COL_DURATION};
        CursorLoader cursorLoader = new CursorLoader(context, WorkInterruption.TimeSheet.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        adapter.swapCursor(null);
    }
}
