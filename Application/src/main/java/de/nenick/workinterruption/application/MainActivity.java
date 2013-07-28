package de.nenick.workinterruption.application;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import de.nenick.workinterruption.R;
import de.nenick.workinterruption.application.functions.GetOpenDoingsLoader;
import de.nenick.workinterruption.dataaccess.TimeSheetTable;
import de.nenick.workinterruption.dataaccess.api.WorkInterruption;


public class MainActivity extends Activity {

    private SwitchListener isWorkActive = new SwitchListener();
    private SwitchListener isBreakActive = new SwitchListener();
    private SwitchListener isMeetingActive = new SwitchListener();
    private SwitchListener isInterruptActive = new SwitchListener();

    private GetOpenDoingsLoader openDoingsLoader = new GetOpenDoingsLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            if( savedInstanceState.getInt("work", 0) > 0) {
                isWorkActive.setBegan(savedInstanceState.getInt("work"));
                ((Switch) findViewById(R.id.work_toggle)).setChecked(true);
            }

            if( savedInstanceState.getInt("break", 0) > 0) {
                isBreakActive.setBegan(savedInstanceState.getInt("break"));
                ((Switch) findViewById(R.id.break_toggle)).setChecked(true);
            }
            if( savedInstanceState.getInt("meeting", 0) > 0) {
                isMeetingActive.setBegan(savedInstanceState.getInt("meeting"));
                ((Switch) findViewById(R.id.meeting_toggle)).setChecked(true);
            }
            if( savedInstanceState.getInt("interrupt", 0) > 0) {
                isInterruptActive.setBegan(savedInstanceState.getInt("interrupt"));
                ((Switch) findViewById(R.id.interrupt_toggle)).setChecked(true);
            }
        } else {
            openDoingsLoader.setCallback(this);
            openDoingsLoader.setContext(this);
            getLoaderManager().initLoader(21, null, openDoingsLoader);
        }


        ((Switch) findViewById(R.id.work_toggle)).setOnCheckedChangeListener(isWorkActive);
        ((Switch) findViewById(R.id.break_toggle)).setOnCheckedChangeListener(isBreakActive);
        ((Switch) findViewById(R.id.meeting_toggle)).setOnCheckedChangeListener(isMeetingActive);
        ((Switch) findViewById(R.id.interrupt_toggle)).setOnCheckedChangeListener(isInterruptActive);
    }

    public void onOpenDoings(Cursor cursor) {
        while (cursor.moveToNext()) {
            long begun = cursor.getLong(cursor.getColumnIndex(TimeSheetTable.COL_BEGAN));
            String category = cursor.getString(cursor.getColumnIndex(TimeSheetTable.COL_CATEGORY));
            if( category.equals(((Switch) findViewById(R.id.work_toggle)).getText())) {
                isWorkActive.setBegan(begun);
                ((Switch) findViewById(R.id.work_toggle)).setChecked(true);
            }

            if( category.equals(((Switch) findViewById(R.id.work_toggle)).getText())) {
                isBreakActive.setBegan(begun);
                ((Switch) findViewById(R.id.break_toggle)).setChecked(true);
            }
            if( category.equals(((Switch) findViewById(R.id.work_toggle)).getText())) {
                isMeetingActive.setBegan(begun);
                ((Switch) findViewById(R.id.meeting_toggle)).setChecked(true);
            }
            if( category.equals(((Switch) findViewById(R.id.work_toggle)).getText())) {
                isInterruptActive.setBegan(begun);
                ((Switch) findViewById(R.id.interrupt_toggle)).setChecked(true);
            }
        }
        getLoaderManager().destroyLoader(21);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    public class SwitchListener implements CompoundButton.OnCheckedChangeListener {

        private long began = 0;

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {

            if(isActive) {
                began = System.currentTimeMillis();
            } else {
                ContentValues values = new ContentValues();
                values.put(TimeSheetTable.COL_CATEGORY, compoundButton.getText().toString());
                values.put(TimeSheetTable.COL_BEGAN, began);
                values.put(TimeSheetTable.COL_DURATION, (int) (System.currentTimeMillis() - began));

                getContentResolver().insert(WorkInterruption.TimeSheet.CONTENT_URI, values);
                began = 0;
            }
        }

        public void setBegan(long began) {
            this.began = began;
        }

        public long getBegan() {
            return began;
        }
    }
}
