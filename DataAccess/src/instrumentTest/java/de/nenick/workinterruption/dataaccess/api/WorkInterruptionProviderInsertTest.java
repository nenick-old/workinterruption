package de.nenick.workinterruption.dataaccess.api;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

import de.nenick.workinterruption.dataaccess.TimeSheetTable;
import de.nenick.workinterruption.dataaccess.api.WorkInterruption;
import de.nenick.workinterruption.dataaccess.api.WorkInterruptionProvider;

public class WorkInterruptionProviderInsertTest extends ProviderTestCase2 {

    public WorkInterruptionProviderInsertTest() {
        super(WorkInterruptionProvider.class, WorkInterruption.AUTHORITY);
    }

    public void testInsertRecord() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(TimeSheetTable.COL_CATEGORY, "testCategory");

        // when: values are insert
        Uri rowUri = getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);

        // then: record was created
        long rowId = ContentUris.parseId(rowUri);
        assertEquals(1, rowId);
    }

    public void testInsertCreateStartTimestamp() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(TimeSheetTable.COL_CATEGORY, "testCategory");

        // when: values are insert
        getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);

        // then: then record contains start timestamp
        String[] projection = {TimeSheetTable._ID, TimeSheetTable.COL_BEGAN, TimeSheetTable.COL_CATEGORY, TimeSheetTable.COL_DURATION};
        Cursor result = getProvider().query(WorkInterruption.TimeSheet.CONTENT_URI, projection, null, null, null);
        result.moveToNext();
        assertTrue("should contains start a timestamp", result.getInt(result.getColumnIndex(TimeSheetTable.COL_BEGAN)) > 0);
    }

    public void testInsertFailsForMissingValues() {

        // given: null values
        ContentValues values = null;

        // when: values are insert
        try {
            getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);
            fail("exception expected");
        } catch (IllegalArgumentException e) {
            // then: record was not inserted
        }
    }

    public void testInsertFailsForMissingCategory() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();

        // when: values are insert
        try {
            getProvider().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);
            fail("exception expected");
        } catch (IllegalArgumentException e) {
            // then: record was not inserted
        }
    }

    public void testInsertFailsForWrongURI() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(TimeSheetTable.COL_CATEGORY, "testCategory");

        // and: wrong uri
        Uri uri = Uri.parse("/wrong_uri");

        // when: values are insert
        try {
            getProvider().insert(uri, values);
            fail("exception expected");
        } catch (IllegalArgumentException e) {
            // then: record was not inserted
        }
    }
}
