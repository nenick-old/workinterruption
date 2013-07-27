package de.nenick.workinterruption.dataaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Helper to create and upgrade the database for work interruptions. */
public class WorkInterruptionSQLite extends SQLiteOpenHelper {

    /** Give the database a unique name inside the app. */
    private static final String DATABASE_NAME = "work_interruption";

    // TODO version should go to a config file and be increased for releases with database changes
    // for test runs ignore this property, database is each time recreated
    private static final int DATABASE_VERSION = 1;

    private TimeSheetTable timeSheetTable = new TimeSheetTable();

    public WorkInterruptionSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        timeSheetTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        timeSheetTable.onUpgrade(database, oldVersion, newVersion);
    }
}
