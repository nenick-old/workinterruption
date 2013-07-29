package de.nenick.workinterruption.application.functions;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

/**
 * Callback handler for asynchronous loading the task list.
 *
 * You must first set the adapter and context before your can init this.
 */
public class GetTaskListDataLoader implements LoaderManager.LoaderCallbacks<Cursor> {

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
        String[] projection = {Task.VALUE_ID, Task.VALUE_STARTED, Task.VALUE_CATEGORY, Task.VALUE_DURATION};
        CursorLoader cursorLoader = new CursorLoader(context, Task.CONTENT_URI, projection, null, null, null);
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
