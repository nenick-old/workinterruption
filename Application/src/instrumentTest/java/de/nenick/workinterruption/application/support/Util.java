package de.nenick.workinterruption.application.support;

import java.util.Calendar;

public class Util {

    static Calendar cal = Calendar.getInstance();
    static {
        cal.set(2012, Calendar.DECEMBER, 21, 0, 0, 0);
    }

    public static long timestamp_Fri_21_12() {
        return cal.getTimeInMillis();
    }
}
