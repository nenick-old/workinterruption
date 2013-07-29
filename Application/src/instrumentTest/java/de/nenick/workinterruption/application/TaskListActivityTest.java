package de.nenick.workinterruption.application;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import de.nenick.workinterruption.application.functions.GetTaskListCursorAdapter;

import static org.mockito.Mockito.mock;

public class TaskListActivityTest extends ActivityInstrumentationTestCase2<TaskListActivity> {

    TaskListActivity activity;

    public TaskListActivityTest() {
        super(TaskListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        TextView view = mock(TextView.class);
    }

    public void testPreconditions() {
        assertNotNull("activity was not created", activity);
    }

    public void testShouldHaveCorrectAdapter() {
        ListView view = (ListView) activity.findViewById(android.R.id.list);
        ListAdapter adapter = view.getAdapter();

        assertEquals(adapter.getClass(), GetTaskListCursorAdapter.class);
    }
}
