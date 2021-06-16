import org.junit.Before;
import org.junit.Test;
import structures.CalendarHandler;
import structures.Event;
import structures.Utility;

import static org.junit.Assert.assertEquals;

public class CalendarHandlerTests {

    @Before
    public void setup() {

    }

    @Test
    public void testAddEvent() {
        CalendarHandler.clearEvents();

        Event e = new Event("21-04-20", "a", "b");
        CalendarHandler.addEvent(e);

        assertEquals(1, CalendarHandler.getEvents().size());
    }

    @Test
    public void testDeleteEvent() {
        CalendarHandler.clearEvents();

        Event e = new Event("21-04-20", "a", "b");
        CalendarHandler.addEvent(e);
        assertEquals(1, CalendarHandler.getEvents().size());
        CalendarHandler.deleteEvent(e);
        assertEquals(0, CalendarHandler.getEvents().size());
    }

    @Test
    public void testToSSV() {
        CalendarHandler.clearEvents();

        Event e = new Event("21-04-20", "a", "b");
        Event e2 = new Event("21-04-21", "b", "c");
        CalendarHandler.addEvent(e);
        CalendarHandler.addEvent(e2);

        assertEquals("21-04-20ඞaඞb\n21-04-21ඞbඞc\n", CalendarHandler.toSSV());
    }
}