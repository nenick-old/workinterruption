package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.widget.CursorAdapter;
import android.widget.ListView;

/** Load and show the tasks list content asynchronous. */
public class GetTaskListFunction {

    /**
     * Show tasks at given list.
     *
     * @param activity activity to init data loader (listView owner)
     * @param listView target for the content
     */
    public void apply(Activity activity, ListView listView) {

        CursorAdapter adapter = new GetTaskListCursorAdapter(listView.getContext(), null, 0);
        listView.setAdapter(adapter);

        GetTaskListDataLoader dataLoader = new GetTaskListDataLoader();
        dataLoader.setCursorAdapter(adapter);
        dataLoader.setContext(listView.getContext());
        activity.getLoaderManager().initLoader(0, null, dataLoader);
    }
}
