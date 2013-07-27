package de.nenick.workinterruption.functions;

import android.content.ContentResolver;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.AdapterView;

import de.nenick.workinterruption.contentprovider.WorkInterruption;

/** Removes the given doing. */
public class DeleteDoingFunction {

    /**
     *
     * AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
     *
     * @param item
     * @param contentResolver
     */
    public void apply(MenuItem item, ContentResolver contentResolver) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Uri uri = Uri.parse(WorkInterruption.TimeSheet.CONTENT_URI + "/" + info.id);
        contentResolver.delete(uri, null, null);
    }
}
