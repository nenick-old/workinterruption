package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.database.Cursor;
import android.widget.Switch;

import de.nenick.workinterruption.application.TaskSwitchManager;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

public class SetStatesFromOpenTasksFuncton {
    public void apply(Cursor cursor, Activity activity, TaskSwitchManager ... switches) {
        while (cursor.moveToNext()) {
            long started = cursor.getLong(cursor.getColumnIndex(Task.VALUE_STARTED));
            String category = cursor.getString(cursor.getColumnIndex(Task.VALUE_CATEGORY));
            int resourceId = cursor.getInt(cursor.getColumnIndex(Task.VALUE_ID));

            TaskSwitchManager taskSwitchManager = findSwitchForCategory(switches, category);
            taskSwitchManager.setStarted(started);
            taskSwitchManager.setResourceId(resourceId);
            ((Switch) activity.findViewById(taskSwitchManager.getViewId())).setChecked(true);

        }
    }

    private TaskSwitchManager findSwitchForCategory(TaskSwitchManager[] switches, String category) {
        for(TaskSwitchManager taskSwitchManager : switches) {
            if(category.equals(taskSwitchManager.getCategory())) {
                return taskSwitchManager;
            }
        }
        return null;
    }
}
