package de.nenick.workinterruption.dataaccess.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/** Holds the history of the tasks. */
public class TaskTable implements BaseColumns {

    /** Table name. */
    public static final String TABLE_NAME = "task";

    /**
     * Column name of the task start time.
     * <P>Type: INTEGER (Calendar.getInstance().getTimeInMillis())</P>
     */
    public static final String COL_STARTED = "started";

    /**
     * Column name of the category to which this task belongs.
     * <P>Type: TEXT (any string)</P>
     */
    public static final String COL_CATEGORY = "category";

    /**
     * Column name for the duration of this task
     * <P>Type: INTEGER (milliseconds)</P>
     */
    public static final String COL_DURATION = "duration";

    private static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY,"
            + COL_STARTED + " INTEGER not null,"
            + COL_CATEGORY + " TEXT not null,"
            + COL_DURATION + " INTEGER"
            + ");";

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {

        Log.w(TaskTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
