package de.nenick.workinterruption.application.functions;

import android.os.Bundle;

import de.nenick.workinterruption.application.TaskSwitchManager;

public class SaveOpenTasksToBundleFunction {

    public void apply(Bundle savedInstanceState, TaskSwitchManager... switches) {
        for(int i = 0; i < switches.length; i++) {
            long started = switches[i].getStarted();
            if(activityWasStarted(started))             {
                savedInstanceState.putLong(switches[i].getCategory(), started);
            }
        }
    }

    private boolean activityWasStarted(long started) {
        return started > 0;
    }
}
