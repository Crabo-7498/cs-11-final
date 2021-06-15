package structures;

import structures.CalendarHandler;
import structures.Event;

import java.io.*;
import java.util.ArrayList;

public class IOHandler {

    private static String separator = "ඞ";

    /**
     * Writes out all data
     *
     * Requires: Nothing
     * Modifies: Nothing
     * Effects: Writes all Events to data
     */
    public static void writeOut(ArrayList<Event> events) {
        try {
            FileWriter fWrite = new FileWriter("data.txt", false);
            BufferedWriter bWrite = new BufferedWriter(fWrite);

            bWrite.write(CalendarHandler.toSSV());

            bWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all data
     *
     * Requires: Nothing
     * Modifies: CalendarHandler.events
     * Effects: Add events from data
     */
    public static void readIn() {

        try {
            FileReader fRead = new FileReader("data.txt");
            BufferedReader bRead = new BufferedReader(fRead);

            String line;

            while ((line = bRead.readLine()) != null) {
                String[] data = line.split(separator);

                CalendarHandler.addEvent(new Event(data[0], data[1], data[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
