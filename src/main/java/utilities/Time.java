package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    long startTime;
    long endTime;
    String cleanStartTime;
    String cleanEndTime;

    static DateFormat df = new SimpleDateFormat("MM.dd.yyyy @ h:mm:ss a");
    static DateFormat sd = new SimpleDateFormat("yyyy.MM.dd-h.mm a");
    public static final int SECONDS_PER_DAY = 24 * 60 * 60; // seconds in 24 hours period

    public Time() {
        super();
    }

    public void setTime(int eventEndTime) {
        startTime = System.currentTimeMillis() / 1000L;
        Date startTimeDate = new Date(startTime * 1000);
        cleanStartTime = df.format(startTimeDate);

        endTime = startTime + eventEndTime;
        Date endTimeDate = new Date(endTime * 1000);
        cleanEndTime = df.format(endTimeDate);


    }

    public long getEventStartTime() {
        return startTime;
    }

    public long getEventEndTime() {
        return endTime;
    }

    public String getCleanEventEndTime() {
        return cleanEndTime;
    }

    public String getCleanEventStartTime() {
        return cleanStartTime;
    }
}
