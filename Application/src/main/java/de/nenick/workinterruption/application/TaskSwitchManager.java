package de.nenick.workinterruption.application;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;
import android.widget.CompoundButton;

import de.nenick.workinterruption.dataaccess.api.WorkInterruption;

import static de.nenick.workinterruption.dataaccess.api.WorkInterruption.Task;

public class TaskSwitchManager implements CompoundButton.OnCheckedChangeListener {

    private String category;
    private int viewId;
    private int resourceId;
    private Activity activity;

    public TaskSwitchManager(String category, int viewId, Activity activity) {

        this.category = category;
        this.viewId = viewId;
        this.activity = activity;
    }

    private long started = 0;

    public int getViewId() {
        return viewId;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {

        if(isActive) {
            started = System.currentTimeMillis();
        } else {
            ContentValues values = new ContentValues();
            values.put(Task.VALUE_DURATION, (int) (System.currentTimeMillis() - started));

            if(resourceId == 0) {
                values.put(Task.VALUE_CATEGORY, category);
                values.put(Task.VALUE_STARTED, started);
                activity.getContentResolver().insert(Task.CONTENT_URI, values);
            } else {
                Uri contentUri = ContentUris.withAppendedId(WorkInterruption.Task.CONTENT_ID_URI_BASE, resourceId);
                activity.getContentResolver().update(contentUri, values, null, null);
            }

            started = 0;
        }
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getStarted() {
        return started;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
