package de.nenick.workinterruption.application.functions;

import android.content.ContentResolver;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.AdapterView;

import de.nenick.workinterruption.dataaccess.api.WorkInterruption;

/** Removes the given task. */
public class DeleteTaskFunction {

    public void apply(MenuItem item, ContentResolver contentResolver) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Uri uri = Uri.parse(WorkInterruption.Task.CONTENT_URI + "/" + info.id);
        contentResolver.delete(uri, null, null);
    }
}
