package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.TaskListActivity;
import de.nenick.workinterruption.application.support.CursorMockBuilder;
import de.nenick.workinterruption.application.support.Util;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Mockito.mock;

public class GetTaskListCursorAdapterTest extends ActivityInstrumentationTestCase2<TaskListActivity> {

    GetTaskListCursorAdapter adapter;
    View layout;
    ListView viewGroup;
    TextView startedView;
    TextView categoryView;
    TextView durationView;
    Activity activity;

    public GetTaskListCursorAdapterTest() {
        super(TaskListActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

        adapter = new GetTaskListCursorAdapter(mock(Context.class), mock(Cursor.class), 0);
        LayoutInflater inflater = LayoutInflater.from(getActivity().getBaseContext());
        viewGroup = new ListView(activity.getBaseContext());
        layout = inflater.inflate(R.layout.task_list_row, viewGroup, false);
        startedView = (TextView) layout.findViewById(R.id.started);
        categoryView = (TextView) layout.findViewById(R.id.category);
        durationView = (TextView) layout.findViewById(R.id.duration);
    }

    public void testPreconditions() {
        assertThat(adapter).isNotNull();
        assertThat(activity).isNotNull();
        assertThat(layout).isNotNull();
        assertThat(viewGroup).isNotNull();
        assertThat(startedView).isNotNull();
        assertThat(categoryView).isNotNull();
        assertThat(durationView).isNotNull();
    }

    public void testShouldUseCorrectLayout() {
        View view = adapter.newView(activity.getBaseContext(), mock(Cursor.class), viewGroup);
        assertThat(view).hasId(R.id.row);
    }

    public void testShouldBindCorrectValuesToViews() {
        Cursor cursor = new CursorMockBuilder()
                .withLong(Task.VALUE_STARTED , Util.timestamp_Fri_21_12())
                .withString(Task.VALUE_CATEGORY, "testCategory")
                .withInt(Task.VALUE_DURATION, 600000).create();

        adapter.bindView(layout, mock(Context.class), cursor);

        assertThat(startedView).containsText("Fri 21.12");
        assertThat(categoryView).containsText("testCategory");
        assertThat(durationView).containsText("10 min");
    }

    public void testShouldRoundUpWhenDurationLessOneMinute() {
        Cursor cursor = new CursorMockBuilder()
                .withInt(Task.VALUE_DURATION, 30000).create();

        adapter.bindView(layout, mock(Context.class), cursor);

        assertThat(durationView).containsText("1 min");
    }

    public void testShouldMarkTaskWithNullDuration() {
        Cursor cursor = new CursorMockBuilder()
                .withInt(Task.VALUE_DURATION, 0).create();

        adapter.bindView(layout, mock(Context.class), cursor);

        assertThat(durationView).containsText("on going");
    }
}
