package de.nenick.workinterruption.contentprovider;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Holds the history of your doings.
 */
public class TimeSheetTable implements BaseColumns {

    /** The table name. */
    public static final String TABLE_NAME = "time_sheet";

    /**
     * Column name of the timestamp where this doing started.
     * <P>Type: INTEGER (Calendar.getInstance().getTimeInMillis())</P>
     */
    public static final String COL_BEGAN = "began";

    /**
     * Column name of the category to which this doing belongs.
     * <P>Type: TEXT (any string)</P>
     */
    public static final String COL_CATEGORY = "category";

    /**
     * Column name for the duration of this doing
     * <P>Type: INTEGER (minutes)</P>
     */
    public static final String COL_DURATION = "duration";

    private static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY,"
            + COL_BEGAN + " INTEGER not null,"
            + COL_CATEGORY + " TEXT not null,"
            + COL_DURATION + " INTEGER"
            + ");";

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {

        Log.w(TimeSheetTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
