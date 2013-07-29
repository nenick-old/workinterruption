package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

import de.nenick.workinterruption.application.TaskSwitchManager;

public class GetOpenTasksFromBundleFunction {

    public void apply(Bundle savedInstanceState, Activity activity, TaskSwitchManager... switches) {
        for(int i = 0; i < switches.length; i++) {
            long started = searchStartedTimeForCategory(savedInstanceState, switches[i]);
            if(startedValueWasStored(started))             {
                switches[i].setStarted(started);
                switchButtonToActive(activity, switches[i]);
            }
        }
    }

    private long searchStartedTimeForCategory(Bundle savedInstanceState, TaskSwitchManager aSwitch) {
        String category = aSwitch.getCategory();
        return savedInstanceState.getLong(category);
    }

    private void switchButtonToActive(Activity view, TaskSwitchManager aSwitch) {
        int resourceId = aSwitch.getViewId();
        final Switch button = (Switch) view.findViewById(resourceId);

//        Handler refresh = new Handler(Looper.getMainLooper());
//        refresh.post(new Runnable() {
//            public void run()
//            {
                button.setChecked(true);
//            }
//        });
    }

    private boolean startedValueWasStored(long value) {
        return value > 0;
    }
}
