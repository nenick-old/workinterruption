package de.nenick.workinterruption.functions;

import android.app.Activity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.contentprovider.TimeSheetTable;

/** Load and show the doings list content asynchronous. */
public class GetDoingListFunction {

    /**
     * Show doings at given list.
     *
     * @param activity activity to init data loader (listView owner)
     * @param listView target for the content
     */
    public void apply(Activity activity, ListView listView) {

        String[] dataToShow = {TimeSheetTable.COL_BEGAN, TimeSheetTable.COL_CATEGORY, TimeSheetTable.COL_DURATION};
        int[] targetFields = new int[]{R.id.began, R.id.category, R.id.duration};

        CursorAdapter adapter = new SimpleCursorAdapter(listView.getContext(), R.layout.doing_row, null, dataToShow, targetFields, 0);
        listView.setAdapter(adapter);

        GetDoingListDataLoader dataLoader = new GetDoingListDataLoader();
        dataLoader.setCursorAdapter(adapter);
        dataLoader.setContext(listView.getContext());
        activity.getLoaderManager().initLoader(0, null, dataLoader);
    }
}
