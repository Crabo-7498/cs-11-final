package structures;

import java.util.ArrayList;

public class CalendarHandler {
    private static final ArrayList<Event> events = new ArrayList<>();

    public static String toSSV() {
        StringBuilder res = new StringBuilder();
        for(Event e : events) {
            res.append(e.toSSV()).append("\n");
        }

        return res.toString();
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void addEvent(Event event) {
        events.add(event);
    }

    public static void saveEvents() {
        IOHandler.writeOut(events);
    }
}
