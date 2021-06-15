package structures;

import java.util.ArrayList;

public class CalendarHandler {
    private static final ArrayList<Event> events = new ArrayList<>();

    public String toTSV() {
        StringBuilder res = new StringBuilder();
        for(Event e : events) {
            res.append(e.toTSV()).append("\t");
        }

        return res.toString();
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }
    public static void addEvent(Event event) {
        events.add(event);
    }
}
