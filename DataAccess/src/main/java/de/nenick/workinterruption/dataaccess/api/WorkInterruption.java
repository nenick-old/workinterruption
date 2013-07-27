package de.nenick.workinterruption.dataaccess.api;

import android.net.Uri;

import de.nenick.workinterruption.dataaccess.TimeSheetTable;

/**
 * Created by nenick on 7/20/13.
 */
public final class WorkInterruption
{
    public static final String AUTHORITY = "de.nenick.workinterruption";

    public static final String PATH_WORKSHEET = "timesheet";

    // This class cannot be instantiated
    private WorkInterruption() {
    }

    /**
     * TimeSheet table contract
     */
    public static final class TimeSheet {

        // This class cannot be instantiated
        private TimeSheet() {}

        /*
         * URI definitions
         */

        /**
         * The scheme part for this provider's URI
         */
        private static final String SCHEME = "content://";

        /*
         * Path parts for the URIs
         */

        /**
         * Path part for the TimeSheet URI
         */
        private static final String PATH_NOTES = "/" + PATH_WORKSHEET;

        /**
         * Path part for the Note ID URI
         */
        private static final String PATH_NOTE_ID = "/" + PATH_WORKSHEET + "/";

        /**
         * 0-relative position of a note ID segment in the path part of a note ID URI
         */
        public static final int NOTE_ID_PATH_POSITION = 1;



        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_NOTES);

        /**
         * The content URI base for a single note. Callers must
         * append a numeric note id to this Uri to retrieve a note
         */
        public static final Uri CONTENT_ID_URI_BASE
                = Uri.parse(SCHEME + AUTHORITY + PATH_NOTE_ID);

        /**
         * The content URI match pattern for a single note, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent.
         */
        public static final Uri CONTENT_ID_URI_PATTERN
                = Uri.parse(SCHEME + AUTHORITY + PATH_NOTE_ID + "/#");

        /*
         * MIME type definitions
         */

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * note.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = TimeSheetTable.COL_BEGAN + " DESC";

    }
}
