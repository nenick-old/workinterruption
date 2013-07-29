package de.nenick.workinterruption.application.functions;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import de.nenick.workinterruption.application.MainActivity;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

/**
 * Callback handler for asynchronous loading the task list.
 *
 * You must first set the adapter and context before your can init this.
 */
public class GetOpenTasksFromProviderLoader implements LoaderManager.LoaderCallbacks<Cursor> {

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
        String[] projection = {Task.VALUE_ID, Task.VALUE_STARTED, Task.VALUE_CATEGORY, Task.VALUE_DURATION};
        CursorLoader cursorLoader = new CursorLoader(context, Task.CONTENT_URI, projection, Task.VALUE_DURATION + " is null ", null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        activity.onOpenTasksLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
