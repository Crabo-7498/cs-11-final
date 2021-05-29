package structures;

import java.util.Date;

public class Event {
    public Date date;
    public String name = "unnamed";

    public Event(Date date, String name) {
        this.date = date;
        this.name = name;
    }
}
