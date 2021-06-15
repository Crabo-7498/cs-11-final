package main;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import structures.CalendarHandler;
import structures.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    public Label lbTitle;
    public GridPane gpaneCalendar;
    public Pagination pgnCalendars;
    public Button btnFwrd;
    public Button btnPrev;
    public Button btnCreateCalendar;
    public Label lbCalendarName;
    public DatePicker dateSelector;
    public TextField fieldNewEvent;

    public void addEvent() {
        if(dateSelector.getValue() == null) return;
        CalendarHandler.addEvent(new Event(dateSelector.getValue().toString().substring(2), fieldNewEvent.getText()));
        System.out.println(dateSelector.getValue().toString().substring(2));
        loadCalendar();
    }

    private void loadCalendar() {
        gpaneCalendar.getChildren().clear();
        String today = getNextDate(0);
        String currentMonth = today.substring(2, 4);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                int daysFromFDate = j + (7 * i);
                String date = getNextDate(daysFromFDate - Integer.parseInt(today.substring(4, 5)) + 1);
                System.out.println(date);
                // Create Individual cells inside GridPane for easier formatting and permanent gridlines
                VBox cell = new VBox();

                // Remove "0" at start of date
                Label dateLabel;
                if(date.charAt(4) == '0')
                    dateLabel = new Label(date.substring(5));
                else
                    dateLabel = new Label(date.substring(4));

                // Move Date Label to correct position
                dateLabel.setTranslateX(5);
                dateLabel.setTranslateY(2);

                // Change font color if date is not in current month
                if(!date.substring(2, 4).equals(currentMonth))
                    dateLabel.setId("dateLabelOther");
                else
                    dateLabel.setId("dateLabel");

                cell.setId("cell");

                cell.getChildren().add(0, dateLabel);

                ArrayList<Event> e = getEventsOnDate(date);

                if(e.size() != 0) {
                    Label l = new Label(e.size() + " Event(s)");
                    l.setId("eventNotificationLabel");
                    l.setTranslateX(4);
                    l.setTranslateY(5);

                    // Creating an On Click Event Handler to display events
                    EventHandler<MouseEvent> onClickedEvent = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println(((Label) cell.getChildren().get(0)).getText());
                            displayEvents(getEventsOnDate(((Label) cell.getChildren().get(0)).getText()));
                        }
                    };

                    cell.addEventFilter(MouseEvent.MOUSE_CLICKED, onClickedEvent);

                    cell.getChildren().add(1, l);
                }


                gpaneCalendar.add(cell, j, i);
            }
        }
    }

    private String getNextDate(int days) {
        final SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format.format(calendar.getTime());
    }

    private ArrayList<Event> getEventsOnDate(String date) {
        ArrayList<Event> list = new ArrayList<>();

        for(Event e : CalendarHandler.getEvents()) {
            if(e.date.equals(date)) {
                list.add(e);
            }
        }

        return list;
    }

    private void displayEvents(ArrayList<Event> events) {

    }
}
