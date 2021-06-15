package structures;

import java.util.Date;

public class Event {
    public String date;
    public String name = "unnamed";

    public Event(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public String toTSV() {
        return this.date + "\t" + this.name;
    }
}
