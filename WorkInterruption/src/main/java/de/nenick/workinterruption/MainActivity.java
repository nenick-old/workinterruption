package de.nenick.workinterruption;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

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

}
