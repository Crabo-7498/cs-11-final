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

    /**
     * String Separated Value to store to data
     *
     * Requires: Nothing
     * Modifies: Nothing
     * Effects: Returns formatted string
     *
     * @return String - Formatted SSV
     */
    public String toSSV() {
        return this.date + separator + this.name + separator + description;
    }

    /**
     * ToString function for listView
     *
     * Requires: Nothing
     * Modifies: Nothing
     * Effects: Returns uppercase name
     *
     * @return String - Name
     */
    public String toString() {
        return name.toUpperCase();
    }
}
