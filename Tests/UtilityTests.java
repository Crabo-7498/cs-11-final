import org.junit.Before;
import org.junit.Test;
import structures.CalendarHandler;
import structures.Event;
import structures.Time;
import structures.Utility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class UtilityTests {

    @Before
    public void setup() {

    }

    @Test
    public void testGetNextDate() {

        Time.localDate = () -> new GregorianCalendar(2021, Calendar.JULY, 31).getTime();
        assertEquals("21-08-12", Utility.getNextDate(12));
    }

    @Test
    public void testGetEventsOnDate() {
        Event e = new Event("21-06-09", "a", "b");
        CalendarHandler.addEvent(e);
        assertEquals(e, Utility.getEventsOnDate("21-06-09").get(0));
    }



}