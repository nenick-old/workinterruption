package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.dataaccess.TimeSheetTable;

/** Load and show the doings list content asynchronous. */
public class GetDoingListFunction {

    /**
     * Show doings at given list.
     *
     * @param activity activity to init data loader (listView owner)
     * @param listView target for the content
     */
    public void apply(Activity activity, ListView listView) {

        CursorAdapter adapter = new GetDoingListCursorAdapter(listView.getContext(), null, 0);
        listView.setAdapter(adapter);

        GetDoingListDataLoader dataLoader = new GetDoingListDataLoader();
        dataLoader.setCursorAdapter(adapter);
        dataLoader.setContext(listView.getContext());
        activity.getLoaderManager().initLoader(0, null, dataLoader);
    }
}
