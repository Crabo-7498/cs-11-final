import org.junit.Before;
import org.junit.Test;
import structures.CalendarHandler;
import structures.Event;
import structures.Utility;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class UtilityTests {

    @Before
    public void setup() {

    }

    @Test
    public void testGetNextDate() {

        // Tests written on June 15 so it wont work if your computer clock isn't set to June 15, simply change actual date to be TODAY'S DATE + 3 DAYS
        assertEquals(Utility.getNextDate(3), "21-06-18");
    }

    @Test
    public void testGetEventsOnDate() {
        Event e = new Event("21-06-09", "a", "b");
        CalendarHandler.addEvent(e);
        assertEquals(Utility.getEventsOnDate("21-06-09").get(0), e);
    }



}