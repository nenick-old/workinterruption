package de.nenick.workinterruption.application;

import android.test.ActivityInstrumentationTestCase2;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.functions.SaveOpenTasksToBundleFunction;
import de.nenick.workinterruption.application.functions.SaveOpenTasksToProviderFunction;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @InjectMocks
    MainActivity activity;

    @Mock
    SaveOpenTasksToProviderFunction saveOpenTasksToProviderFunction;

    @Mock
    SaveOpenTasksToBundleFunction saveOpenTasksToBundleFunction;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

        MockitoAnnotations.initMocks(this);
    }

    public void testLayout() {

        assertNotNull(activity.findViewById(R.id.work_toggle));
        assertNotNull(activity.findViewById(R.id.break_toggle));
        assertNotNull(activity.findViewById(R.id.meeting_toggle));
        assertNotNull(activity.findViewById(R.id.interrupt_toggle));
    }

    public void testShouldSaveOpenTasksStateAtPause() {
        getInstrumentation().callActivityOnPause(activity);
        //verify(saveOpenTasksToProviderFunction).apply();
        //verify(saveOpenTasksToBundleFunction).apply();
    }
}
