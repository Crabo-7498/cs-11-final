package structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CalendarView {
    private ArrayList<Event> events = new ArrayList<>();
    private String name;

    public CalendarView(String name) {
        this.name = name;
    }
    public String toTSV() {
        String res = "";
        for(Event e : events) {
            res += (e.toTSV() + "\t");
        }

        return res;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }
}
