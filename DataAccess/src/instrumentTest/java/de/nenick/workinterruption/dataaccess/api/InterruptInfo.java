package de.nenick.workinterruption.dataaccess.api;

import android.content.ContentValues;

import de.nenick.workinterruption.dataaccess.database.TaskTable;

// TODO delete after have example for insert / delete ...
// A utility for converting category data to a ContentValues map.
public class InterruptInfo {
    long day;
    String category;
    long startDate;
    long ended;

    /*
     * Constructor for a InterruptInfo instance. This class helps create a category and
     * return its values in a ContentValues map expected by data model methods.
     * The category's id is created automatically when it is inserted into the data model.
     */
    public InterruptInfo(long t, String n) {
        day = t;
        category = n;
        startDate = 0;
        ended = 0;
    }

    // Sets the creation date for a test category
    public void setStartDate(long c) {
        startDate = c;
    }

    // Sets the modification date for a test category
    public void setEndDate(long m) {
        ended = m;
    }

    /*
     * Returns a ContentValues instance (a map) for this InterruptInfo instance. This is useful for
     * inserting a InterruptInfo into a database.
     */
    public ContentValues getContentValues() {
        // Gets a new ContentValues object
        ContentValues v = new ContentValues();

        // Adds map entries for the user-controlled fields in the map
        v.put(TaskTable.COL_STARTED, day);
        v.put(TaskTable.COL_CATEGORY, category);
        v.put(TaskTable.COL_DURATION, ended);
        return v;

    }
}
