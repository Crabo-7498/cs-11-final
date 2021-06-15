package structures;

public class Event {
    public String date;
    public String name;
    public String description;

    private String separator = "ඞ";

    public Event(String date, String name, String description) {
        this.date = date;
        this.name = name;

        if(description.isEmpty()) this.description = "No Description";
        else this.description = description;
    }

    public String toSSV() {
        return this.date + separator + this.name + separator + description;
    }

    public String toString() {
        return name.toUpperCase();
    }
}
