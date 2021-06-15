package structures;

import java.util.Date;

public class Event {
    public String date;
    public String name;
    public String description;

    public Event(String date, String name, String description) {
        this.date = date;
        this.name = name;

        if(description.isEmpty()) this.description = "No Description";
        else this.description = description;
    }

    public String toTSV() {
        return this.date + "\t" + this.name;
    }

    public String toString() {
        return name.toUpperCase();
    }
}
