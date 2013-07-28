package de.nenick.workinterruption.application;


import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import de.nenick.workinterruption.application.functions.GetDoingListCursorAdapter;
import de.nenick.workinterruption.application.support.MockWorkaround;
import de.nenick.workinterruption.application.support.ViewMock;

import static org.mockito.Mockito.mock;

public class DoingListActivityTest  extends ActivityInstrumentationTestCase2<DoingListActivity> {

    DoingListActivity activity;

    public DoingListActivityTest() {
        super(DoingListActivity.class);
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

        assertEquals(adapter.getClass(), GetDoingListCursorAdapter.class);
    }
}
