package de.nenick.workinterruption.application.functions;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Switch;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.MainActivity;
import de.nenick.workinterruption.application.TaskSwitchManager;
import de.nenick.workinterruption.application.support.Util;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

public class GetOpenTasksFromBundleFunctionTest extends ActivityInstrumentationTestCase2<MainActivity> {

    GetOpenTasksFromBundleFunction getOpenTasksFromBundleFunction;

    Switch alphaSwitch;
    final String ALPHA_TASK = "alpha";
    final int ALPHA_VIEW_ID = R.id.work_toggle;
    TaskSwitchManager alphaTaskSwitchManager;

    Switch betaSwitch;
    final String BETA_TASK = "beta";
    final int BETA_VIEW_ID = R.id.meeting_toggle;
    TaskSwitchManager betaTaskSwitchManager;

    public GetOpenTasksFromBundleFunctionTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getOpenTasksFromBundleFunction = new GetOpenTasksFromBundleFunction();
        alphaTaskSwitchManager = new TaskSwitchManager(ALPHA_TASK, ALPHA_VIEW_ID, getActivity());
        alphaSwitch = (Switch) getActivity().findViewById(ALPHA_VIEW_ID);
        betaTaskSwitchManager = new TaskSwitchManager(BETA_TASK, BETA_VIEW_ID, getActivity());
        betaSwitch = (Switch) getActivity().findViewById(BETA_VIEW_ID);
    }

    public void testPreconditions() {
        assertThat(getOpenTasksFromBundleFunction).isNotNull();
        assertThat(alphaTaskSwitchManager).isNotNull();
        assertThat(alphaSwitch).isNotNull();
        assertThat(betaTaskSwitchManager).isNotNull();
        assertThat(betaSwitch).isNotNull();
    }

    public void testShouldSaveNothingIfNoActiveTask() {
        Bundle bundle = new Bundle();

        getOpenTasksFromBundleFunction.apply(bundle, getActivity(), alphaTaskSwitchManager, betaTaskSwitchManager);

        assertThat(alphaSwitch).isNotChecked();
        assertThat(alphaTaskSwitchManager.getStarted()).isEqualTo(0);

        assertThat(betaSwitch).isNotChecked();
        assertThat(betaTaskSwitchManager.getStarted()).isEqualTo(0);
    }

    public void testShouldSetCheckedAndStartedOnlyForActiveTask() throws Throwable {
        final Bundle bundle = new Bundle();
        bundle.putLong(ALPHA_TASK, Util.timestamp_Fri_21_12());

        runTestOnUiThread(new Runnable() {
            public void run() {
                getOpenTasksFromBundleFunction.apply(bundle, getActivity(), alphaTaskSwitchManager, betaTaskSwitchManager);
            }
        });

        assertThat(alphaSwitch).isChecked();
        assertThat(alphaTaskSwitchManager.getStarted()).isEqualTo(Util.timestamp_Fri_21_12());

        assertThat(betaSwitch).isNotChecked();
        assertThat(betaTaskSwitchManager.getStarted()).isEqualTo(0);
    }
}
