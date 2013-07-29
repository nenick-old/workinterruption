package de.nenick.workinterruption.dataaccess.api;

import android.net.Uri;

import de.nenick.workinterruption.dataaccess.database.TaskTable;

public interface WorkInterruption
{
    String AUTHORITY = "de.nenick.workinterruption";

    String PATH_TASK = "task";

    /** Task resource contract. */
    interface Task {

        /* Value identifiers for the cursor. */

        /** Unique task identifier.
         * <P>Type: INTEGER (generated)</P> */
        String VALUE_ID = TaskTable._ID;

        /** Task start time.
         * <P>Type: long (Calendar.getInstance().getTimeInMillis())</P> */
        String VALUE_STARTED = TaskTable.COL_STARTED;

        /** Task category.
         * <P>Type: TEXT (any string)</P> */
        String VALUE_CATEGORY = TaskTable.COL_CATEGORY;

        /** Task duration.
         * <P>Type: INTEGER (milliseconds)</P> */
        String VALUE_DURATION = TaskTable.COL_DURATION;

        /* Path parts for the URIs */

        /** 0-relative position of Task ID segment in the path part of Task ID URI */
        int PATH_POSITION_TASK_ID = 1;

        /** The content:// style URL for this resource */
        Uri CONTENT_URI =  Uri.parse("content://" + AUTHORITY + "/" + PATH_TASK);

        /** The content URI base for a single task. Callers must
         * append a numeric task id to this Uri to retrieve a task */
        Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/" + PATH_TASK + "/");

        /** The content URI match pattern for a single task, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent. */
        Uri CONTENT_ID_URI_PATTERN = Uri.parse("content://" + AUTHORITY + "/" + PATH_TASK + "/#");

        /* MIME type definitions */

        /** The MIME type of {@link #CONTENT_URI} providing a directory of tasks. */
        String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";

        /** The MIME type of a {@link #CONTENT_URI} sub-directory of a single task. */
        String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";

        /* Some extras */

        /** The default sort order for this table */
        String DEFAULT_SORT_ORDER = TaskTable.COL_STARTED + " DESC";
    }
}
