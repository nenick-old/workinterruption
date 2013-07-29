package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.content.ContentValues;

import de.nenick.workinterruption.application.TaskSwitchManager;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

public class SaveOpenTasksToProviderFunction {

    public void apply(Activity activity, TaskSwitchManager... switches) {
        for(int i = 0; i < switches.length; i++) {
            long started = switches[i].getStarted();
            if(activityWasStarted(started) && notAlreadySaved(switches[i].getResourceId())) {
                ContentValues values = new ContentValues();
                values.put(Task.VALUE_STARTED, started);
                values.put(Task.VALUE_CATEGORY, switches[i].getCategory());
                activity.getContentResolver().insert(Task.CONTENT_URI, values);
            }
        }
    }

    private boolean notAlreadySaved(int resourceId) {
        return resourceId == 0;
    }

    private boolean activityWasStarted(long started) {
        return started > 0;
    }

}
