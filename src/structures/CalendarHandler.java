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

    public static CalendarView getCalendar(int index) {
        return calendars.get(index);
    }

    public static int getSize() {
        return calendars.size();
    }

    public static String toTSV() {
        String res = "";
        for(CalendarView c : calendars) {
            res += c.toTSV();
        }

        return res;
    }
}
