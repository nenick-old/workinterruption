package de.nenick.workinterruption.application;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.functions.GetOpenTasksFromBundleFunction;
import de.nenick.workinterruption.application.functions.GetOpenTasksFromProviderFunction;
import de.nenick.workinterruption.application.functions.GetTaskListFunction;
import de.nenick.workinterruption.application.functions.SaveOpenTasksToBundleFunction;
import de.nenick.workinterruption.application.functions.SaveOpenTasksToProviderFunction;
import de.nenick.workinterruption.application.functions.SetStatesFromOpenTasksFuncton;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;


public class MainActivity extends Activity {

    private TaskSwitchManager isWorkActive = new TaskSwitchManager("work", R.id.work_toggle, this);
    private TaskSwitchManager isBreakActive = new TaskSwitchManager("break", R.id.break_toggle, this);
    private TaskSwitchManager isMeetingActive = new TaskSwitchManager("meeting", R.id.meeting_toggle, this);
    private TaskSwitchManager isInterruptActive = new TaskSwitchManager("interruption", R.id.interrupt_toggle, this);
    private TaskSwitchManager[] allTaskManager = new TaskSwitchManager[]{isWorkActive, isBreakActive, isMeetingActive, isInterruptActive};


    private GetOpenTasksFromProviderFunction getOpenTasksFromProviderFunction = new GetOpenTasksFromProviderFunction();
    private GetOpenTasksFromBundleFunction getOpenTasksFromBundleFunction = new GetOpenTasksFromBundleFunction();
    private SaveOpenTasksToProviderFunction saveOpenTasksToProviderFunction = new SaveOpenTasksToProviderFunction();
    private SaveOpenTasksToBundleFunction saveOpenTasksToBundleFunction = new SaveOpenTasksToBundleFunction();
    private SetStatesFromOpenTasksFuncton setStatesFromOpenTasksFuncton = new SetStatesFromOpenTasksFuncton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            getOpenTasksFromBundleFunction.apply(savedInstanceState, this, allTaskManager);
        }

        ((Switch) findViewById(R.id.work_toggle)).setOnCheckedChangeListener(isWorkActive);
        ((Switch) findViewById(R.id.break_toggle)).setOnCheckedChangeListener(isBreakActive);
        ((Switch) findViewById(R.id.meeting_toggle)).setOnCheckedChangeListener(isMeetingActive);
        ((Switch) findViewById(R.id.interrupt_toggle)).setOnCheckedChangeListener(isInterruptActive);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //new GetTaskListFunction().apply(this, new ListView(getBaseContext()));
        getOpenTasksFromProviderFunction.apply(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getOpenTasksFromBundleFunction.apply(savedInstanceState, this, allTaskManager);
    }

    public void onOpenTasksLoaded(Cursor cursor) {
        setStatesFromOpenTasksFuncton.apply(cursor, this, allTaskManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveOpenTasksToProviderFunction.apply(this, allTaskManager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveOpenTasksToBundleFunction.apply(outState, allTaskManager);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Reaction to the menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                Intent i = new Intent(this, TaskListActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
