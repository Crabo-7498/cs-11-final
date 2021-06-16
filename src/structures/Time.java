package structures;

import java.util.Date;
import java.util.function.Supplier;

/**
 * Wrapper class to make Unit Tests reproducible
 */
public class Time {

    // Returns current date, but is overwritten in unit tests
    public static Supplier<Date> localDate = Date::new;
}
