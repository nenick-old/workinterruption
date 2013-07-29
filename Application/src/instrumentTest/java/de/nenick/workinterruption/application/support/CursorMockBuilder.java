package de.nenick.workinterruption.application.support;

import android.database.Cursor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CursorMockBuilder {

    Cursor cursor = mock(Cursor.class);
    int index = 0;

    public CursorMockBuilder withLong(String key, long value) {
        when(cursor.getColumnIndex(key)).thenReturn(index);
        when(cursor.getLong(index)).thenReturn(value);
        index++;
        return this;
    }


    public CursorMockBuilder withString(String key, String value) {
        when(cursor.getColumnIndex(key)).thenReturn(index);
        when(cursor.getString(index)).thenReturn(value);
        index++;
        return this;
    }


    public CursorMockBuilder withInt(String key, int value) {
        when(cursor.getColumnIndex(key)).thenReturn(index);
        when(cursor.getInt(index)).thenReturn(value);
        index++;
        return this;
    }

    public Cursor create() {
        return cursor;
    }
}
