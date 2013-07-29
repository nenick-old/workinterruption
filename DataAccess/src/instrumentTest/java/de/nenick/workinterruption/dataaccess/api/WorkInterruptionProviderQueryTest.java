package de.nenick.workinterruption.dataaccess.api;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.ProviderTestCase2;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

public class WorkInterruptionProviderQueryTest extends ProviderTestCase2 {

    public WorkInterruptionProviderQueryTest() {
        super(WorkInterruptionProvider.class, WorkInterruption.AUTHORITY);
    }

    public void testQueryListOfWorkInterruptions() {

        // given: some record
        ContentValues values = new ContentValues();
        values.put(Task.VALUE_CATEGORY, "testCategory");
        getProvider().insert(Task.CONTENT_URI, values);
        getProvider().insert(Task.CONTENT_URI, values);

        // when: query for list
        String[] projection = {Task.VALUE_ID, Task.VALUE_STARTED, Task.VALUE_CATEGORY, Task.VALUE_DURATION};
        Cursor result = getProvider().query(Task.CONTENT_URI, projection, Task.VALUE_DURATION + " is null ", null, null);

        // then: the result contains all records
        assertEquals(2, result.getCount());
    }

    public void testShouldProduceEmptyCursor() {

        // when: query for list
        String[] projection = {Task.VALUE_ID, Task.VALUE_STARTED, Task.VALUE_CATEGORY, Task.VALUE_DURATION};
        Cursor result = getProvider().query(Task.CONTENT_URI, projection, Task.VALUE_DURATION + " is null ", null, null);

        // then: the result contains all records
        assertEquals(0, result.getCount());
    }
}
