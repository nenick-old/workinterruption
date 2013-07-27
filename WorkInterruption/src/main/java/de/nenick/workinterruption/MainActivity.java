package de.nenick.workinterruption;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.nenick.workinterruption.contentprovider.TimeSheetTable;
import de.nenick.workinterruption.contentprovider.WorkInterruption;

public class MainActivity extends Activity {

    private boolean isWorkActive = false;
    private boolean isBreakActive = false;
    private boolean isMeetingActive = false;
    private boolean isInterruptActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View view) {
        if(view.getId() == R.id.work_toggle) {
            isWorkActive = switchState(R.id.work_status, isWorkActive);

            ContentValues v = new ContentValues();
            v.put(TimeSheetTable.COL_CATEGORY, "Test inserting a category");
            v.put(TimeSheetTable.COL_BEGAN, System.currentTimeMillis());

            Uri rowUri = getContentResolver().insert(WorkInterruption.TimeSheet.CONTENT_URI, v);
        }
        if(view.getId() == R.id.break_toggle) {
            isBreakActive = switchState(R.id.break_status, isBreakActive);
        }
        if(view.getId() == R.id.meeting_toggle) {
            isMeetingActive = switchState(R.id.meeting_status, isMeetingActive);
        }
        if(view.getId() == R.id.interrupt_toggle) {
            isInterruptActive = switchState(R.id.interrupt_status, isInterruptActive);
        }
    }

    private boolean switchState(int statusView, boolean isActive) {
        TextView status = (TextView)findViewById(statusView);
        if(isActive) {
            status.setText(R.string.status_stopped);
            return false;
        } else {
            status.setText(R.string.status_active);
            return true;
        }
    }

    // Reaction to the menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                Intent i = new Intent(this, DoingListActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
