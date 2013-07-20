package de.nenick.workinterruption.contentprovider;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TimeSheetTable {

    private static final String DATABASE_CREATE = "CREATE TABLE "
            + WorkInterruption.TimeSheet.TABLE_NAME + " ("
                + WorkInterruption.TimeSheet._ID + " INTEGER PRIMARY KEY,"
                + WorkInterruption.TimeSheet.COL_DAY + " TEXT,"
                + WorkInterruption.TimeSheet.COL_CATEGORY + " TEXT,"
                + WorkInterruption.TimeSheet.COL_START + " INTEGER,"
                + WorkInterruption.TimeSheet.COL_END + " INTEGER"
            + ");";

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        Log.w(TimeSheetTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion + ", which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS " + WorkInterruption.TimeSheet.TABLE_NAME);
        onCreate(database);
    }
}
