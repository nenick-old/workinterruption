package de.nenick.workinterruption.application.functions;


import android.app.Activity;
import android.database.Cursor;
import android.widget.CursorAdapter;

import de.nenick.workinterruption.application.MainActivity;
import de.nenick.workinterruption.application.TaskSwitchManager;

public class GetOpenTasksFromProviderFunction {

    public void apply(MainActivity activity, TaskSwitchManager... switches) {
        GetOpenTasksFromProviderLoader dataLoader = new GetOpenTasksFromProviderLoader();
        dataLoader.setCallback(activity);
        dataLoader.setContext(activity.getBaseContext());
        activity.getLoaderManager().initLoader(1, null, dataLoader);
    }
}
