package structures;

import java.util.ArrayList;

public class CalendarHandler {
    private static final ArrayList<Event> events = new ArrayList<>();

    /**
     * String Separated Value to store to data
     *
     * Requires: Nothing
     * Modifies: Nothing
     * Effects: Returns formatted string
     *
     * @return String - Formatted SSV
     */
    public static String toSSV() {

        // Separate Events with a new line
        StringBuilder res = new StringBuilder();
        for(Event e : events) {
            res.append(e.toSSV()).append("\n");
        }

        return res.toString();
    }

    /**
     * Save events to data
     *
     * Requires: Nothing
     * Modifies: Nothing
     * Effects: Calls IOHandler to write out EVENTS
     */
    public static void saveEvents() {
        IOHandler.writeOut(events);
    }

    // Getters and Setters
    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void addEvent(Event event) {
        events.add(event);
    }

    public static void deleteEvent(Event event) {
        events.remove(event);
    }
}
