package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import structures.CalendarHandler;
import structures.Event;
import structures.IOHandler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    public Label lbTitle;
    public GridPane gpaneCalendar;
    public Button btnFwrd;
    public Button btnPrev;
    public Button btnCreateCalendar;
    public Label lbCalendarTitle;
    public Label lbTodayEvents;
    public DatePicker dateSelector;
    public TextField fieldNewEvent;
    public ListView<Event> listEvents = new ListView<>();
    public Label labelEventName;
    public TextField fieldDescription;
    public Label labelDescription;

    private int dateOffset = 0;
    private boolean loaded = false;

    public void addEvent() {
        if(dateSelector.getValue() == null || fieldNewEvent.getText().isEmpty()) return;
        CalendarHandler.addEvent(new Event(dateSelector.getValue().toString().substring(2), fieldNewEvent.getText(), fieldDescription.getText()));

        fieldNewEvent.clear();
        fieldDescription.clear();
        loadCalendar();
    }

    private void loadCalendar() {
        CalendarHandler.saveEvents();

        gpaneCalendar.getChildren().clear();
        String today = getNextDate(0);

        lbCalendarTitle.setText("TODAY - " + today);
        lbTodayEvents.setText(getEventsOnDate(today).size() + " event(s) today");

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                int daysFromFDate = j + (7 * i) + dateOffset;
                String date = getNextDate(daysFromFDate - Integer.parseInt(today.substring(4, 5)) + 1);

                // Create Individual cells inside GridPane for easier formatting and permanent gridlines
                VBox cell = new VBox();

                // Give it default text and ID
                Label dateLabel = new Label(date);
                dateLabel.setId("dateLabel");

                // Change CSS if today
                if(date.equals(today)) dateLabel.setId("todayDateLabel");

                // Move Date Label to correct position
                dateLabel.setTranslateX(5);
                dateLabel.setTranslateY(2);

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
                            displayEventList(getEventsOnDate(((Label) cell.getChildren().get(0)).getText()));
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

    private void displayEventList(ArrayList<Event> events) {
        listEvents.getItems().clear();
        for(Event e : events) {
            listEvents.getItems().add(e);
        }
    }

    public void loadEvent(MouseEvent mouseEvent) {
        Event e = listEvents.getSelectionModel().getSelectedItem();
        if(e == null) return;
        labelEventName.setText(e.name.toUpperCase());
        labelDescription.setText(e.description);
    }

    public void calendarNext(ActionEvent actionEvent) {
        dateOffset += 7;
        loadCalendar();
    }

    public void calendarPrev(ActionEvent actionEvent) {
        dateOffset -= 7;
        loadCalendar();
    }

    public void loadFromData(MouseEvent mouseEvent) {
        if(loaded == true) return;
        loaded = true;
        IOHandler.readIn();
        loadCalendar();
    }
}
