package structures;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utility {
    /**
     * Gets a future date given the amount of days it is from today
     *
     * Requires: Int - Number of days from today
     * Modifies: Nothing
     * Effects: Calculates and returns formatted date
     *
     * @return String - formatted date YY-MM-DD
     */
    public static String getNextDate(int days) {

        // Creates a date format, e.g. I am writing this as of Jun 15 2021, so it would display 21-06-15
        // A date and calendar are also created for the next part
        final SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();

        // Sets the calendar time to today's date and adds DAYS amount of days
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);

        return format.format(calendar.getTime());
    }

    /**
     * Get all events on a given date
     *
     * Requires: String - A formatted date in YY-MM-DD
     * Modifies: Nothing
     * Effects: Returns and arraylist of all events on that date
     *
     * @return - ArrayList - All events on DATE
     */
    public static ArrayList<Event> getEventsOnDate(String date) {

        // Check if null
        if(date == null) return null;

        // Creates the arraylist that we will return later
        ArrayList<Event> list = new ArrayList<>();

        // Iterates through the events and checks if their date is equal to the selected date
        // Adds them to the arraylist if yes
        for(Event e : CalendarHandler.getEvents()) {
            if(e.date.equals(date)) {
                list.add(e);
            }
        }

        return list;
    }
}
