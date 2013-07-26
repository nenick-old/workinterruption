package de.nenick.workinterruption.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.ProviderTestCase2;

public class WorkInterruptionProviderQueryTest extends ProviderTestCase2 {

    public WorkInterruptionProviderQueryTest() {
        super(WorkInterruptionProvider.class, WorkInterruption.AUTHORITY);
    }

    public void testQueryListOfWorkInterruptions() {

        // given: some record
        ContentValues values = new ContentValues();
        values.put(TimeSheetTable.COL_CATEGORY, "testCategory");
        getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);
        getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);

        // when: query for list
        String[] projection = {TimeSheetTable._ID, TimeSheetTable.COL_BEGAN, TimeSheetTable.COL_CATEGORY, TimeSheetTable.COL_DURATION};
        Cursor result = getProvider().query(WorkInterruption.TimeSheet.CONTENT_URI, projection, null, null, null);

        // then: the result contains all records
        assertEquals(2, result.getCount());
    }

}
