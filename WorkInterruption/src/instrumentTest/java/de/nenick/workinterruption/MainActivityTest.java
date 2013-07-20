package de.nenick.workinterruption;

import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testLayout() {
        assertNotNull(activity.findViewById(R.id.work_toggle));
        assertNotNull(activity.findViewById(R.id.work_status));

        assertNotNull(activity.findViewById(R.id.break_toggle));
        assertNotNull(activity.findViewById(R.id.break_status));

        assertNotNull(activity.findViewById(R.id.meeting_toggle));
        assertNotNull(activity.findViewById(R.id.meeting_status));

        assertNotNull(activity.findViewById(R.id.interrupt_toggle));
        assertNotNull(activity.findViewById(R.id.interrupt_status));
    }
}
