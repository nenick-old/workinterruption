package de.nenick.workinterruption.application.functions;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.mock.MockContext;
import android.test.mock.MockCursor;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.DoingListActivity;
import de.nenick.workinterruption.dataaccess.TimeSheetTable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetDoingListCursorAdapterTest extends ActivityInstrumentationTestCase2<DoingListActivity> {

    GetDoingListCursorAdapter adapter;
    View view;

    public GetDoingListCursorAdapterTest() {
        super(DoingListActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        adapter = new GetDoingListCursorAdapter(mock(Context.class), mock(Cursor.class), 0);
        LayoutInflater inflater = LayoutInflater.from(getActivity().getBaseContext());
        view = inflater.inflate(R.layout.doing_row, new ListView(getActivity().getBaseContext()), false);
    }

    public void testShouldSetCorrectValues() {

        Calendar cal = Calendar.getInstance();
        cal.set(2000, 01, 01, 14, 30, 00);

        Cursor cursor = mock(Cursor.class);
        when(cursor.getColumnIndex(TimeSheetTable.COL_BEGAN)).thenReturn(1);
        when(cursor.getLong(1)).thenReturn(cal.getTimeInMillis());

        when(cursor.getColumnIndex(TimeSheetTable.COL_CATEGORY)).thenReturn(2);
        when(cursor.getString(2)).thenReturn("testCategory");

        when(cursor.getColumnIndex(TimeSheetTable.COL_DURATION)).thenReturn(3);
        when(cursor.getInt(3)).thenReturn(12345);

        adapter.bindView(view, mock(Context.class), cursor);

        TextView textView = (TextView) view.findViewById(R.id.began);
        assertEquals("Tue 1.2", textView.getText());

        TextView categoryView = (TextView) view.findViewById(R.id.category);
        assertEquals("testCategory", categoryView.getText());

        TextView durationView = (TextView) view.findViewById(R.id.duration);
        assertEquals("12345", durationView.getText());
    }

}
