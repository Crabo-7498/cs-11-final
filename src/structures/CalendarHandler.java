package structures;

import java.util.ArrayList;

public class CalendarHandler {
    private static final ArrayList<CalendarView> calendars = new ArrayList<>();

    public static void addCalendar(CalendarView cal) {
        calendars.add(cal);
    }

    public static void removeCalendar(CalendarView cal) {
        calendars.add(cal);
    }

    public static String toTSV() {
        String res = "";
        for(CalendarView c : calendars) {
            res += c.toTSV();
        }

        return res;
    }
}
