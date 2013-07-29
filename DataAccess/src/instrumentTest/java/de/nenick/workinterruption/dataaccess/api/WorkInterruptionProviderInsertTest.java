package de.nenick.workinterruption.dataaccess.api;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

public class WorkInterruptionProviderInsertTest extends ProviderTestCase2 {

    public WorkInterruptionProviderInsertTest() {
        super(WorkInterruptionProvider.class, WorkInterruption.AUTHORITY);
    }

    public void testInsertRecord() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(Task.VALUE_CATEGORY, "testCategory");

        // when: values are insert
        Uri rowUri = getProvider().insert(Task.CONTENT_URI, values);

        // then: record was created
        long rowId = ContentUris.parseId(rowUri);
        assertEquals(1, rowId);
    }

    public void testInsertCreateStartTimestamp() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(Task.VALUE_CATEGORY, "testCategory");

        // when: values are insert
        getProvider().insert(Task.CONTENT_URI, values);

        // then: then record contains start timestamp
        String[] projection = {Task.VALUE_ID, Task.VALUE_STARTED, Task.VALUE_CATEGORY, Task.VALUE_DURATION};
        Cursor result = getProvider().query(Task.CONTENT_URI, projection, null, null, null);
        result.moveToNext();
        assertTrue("should contains start timestamp", result.getInt(result.getColumnIndex(Task.VALUE_STARTED)) > 0);
    }

    public void testInsertFailsForMissingValues() {

        // given: null values
        ContentValues values = null;

        // when: values are insert
        try {
            getProvider().insert(Task.CONTENT_URI, values);
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
            getProvider().insert(Task.CONTENT_URI, values);
            fail("exception expected");
        } catch (IllegalArgumentException e) {
            // then: record was not inserted
        }
    }

    public void testInsertFailsForWrongURI() {

        // given: necessary values for a record
        ContentValues values = new ContentValues();
        values.put(Task.VALUE_CATEGORY, "testCategory");

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
