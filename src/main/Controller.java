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
import structures.Utility;

import java.text.SimpleDateFormat;
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
    public TextField fieldDescription;
    public Label lbEventName;
    public Label lbDescription;
    public Button btnDelete;

    private int dateOffset = 0;
    private boolean loaded = false;
    private String currentSelectedDate;

    /**
     * Renders the calendar with dates, events, etc
     *
     * Requires: Nothing
     * Modifies: gPaneCalendar, lbCalendarTitle, lbTodayEvents
     * Effects: Renders Calendar to GUI
     */
    private void loadCalendar() {

        // Save all events to Data
        CalendarHandler.saveEvents();

        // Clear the grid pane to avoid duplication and calculates todays date
        gpaneCalendar.getChildren().clear();
        String today = Utility.getNextDate(0);

        // Set the title and the number of event today
        lbCalendarTitle.setText("TODAY - " + today);
        lbTodayEvents.setText(Utility.getEventsOnDate(today).size() + " event(s) today");

        // Iterated through each cell of the displayed calendar
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {

                // Calculates the first date to be displayed with its (X, Y) position in the calendar grid
                // Gets the current (today) date by doing some math (i wrote this equation some time ago so idk what it does either - but at least it works)
                int daysFromFDate = j + (7 * i) + dateOffset;
                String date = Utility.getNextDate(daysFromFDate - Integer.parseInt(today.substring(4, 5)) + 1);

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

                // Sets the cell id and adds the date label to it
                cell.setId("cell");
                cell.getChildren().add(0, dateLabel);

                // Creates an arraylist of events on this day (note this can be empty if there are no events)
                ArrayList<Event> e = Utility.getEventsOnDate(date);

                // Checks if E is empty
                if(e.size() != 0) {

                    // Creates a label that will display the number of events and formats it
                    Label l = new Label(e.size() + " Event(s)");
                    l.setId("eventNotificationLabel");
                    l.setTranslateX(4);
                    l.setTranslateY(5);

                    // Create an onclick event that will display the event list
                    EventHandler<MouseEvent> onClickedEvent = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            currentSelectedDate = ((Label) cell.getChildren().get(0)).getText();
                            displayEventList(Utility.getEventsOnDate(currentSelectedDate));
                        }
                    };

                    // Add the onclick event to cells that have 1+ events
                    // Then adds the event label to the cell
                    cell.addEventFilter(MouseEvent.MOUSE_CLICKED, onClickedEvent);
                    cell.getChildren().add(1, l);
                }

                // Adds the cell at the correct (X, Y) grid pane position
                gpaneCalendar.add(cell, j, i);
            }
        }
    }


    /**
     * Displays all events on a selected date to the event list
     *
     * Requires: An arraylists of all the events on that date
     * Modifies: listEvents
     * Effects: Renders the event list (list view) with all the events of the selected date
     *
     * @param events
     */
    private void displayEventList(ArrayList<Event> events) {

        // Check if null
        if(events == null) return;

        // Clear all events to avoid duplication
        // Iterates through the events to add each of them to the event list
        listEvents.getItems().clear();
        for(Event e : events) {
            listEvents.getItems().add(e);
        }
    }

    /**
     * Scroll down
     *
     * Requires: Nothing
     * Modifies: dateOffset
     * Effects: Scroll the calendar down and re-render it
     */
    public void calendarNext(ActionEvent actionEvent) {
        dateOffset += 7;
        loadCalendar();
    }

    /**
     * Scroll up
     *
     * Requires: Nothing
     * Modifies: dateOffset
     * Effects: Scroll the calendar up and re-render it
     */
    public void calendarPrev(ActionEvent actionEvent) {
        dateOffset -= 7;
        loadCalendar();
    }

    /**
     * Adds an event after using the delete button
     *
     * Requires: Nothing
     * Modifies: lbEventName, lbDescription, btnDelete, CalendarHandler.events
     * Effects: Adds the event to CalendarHandler.events
     *          Sets name and description + enables delete button
     */
    public void addEvent() {

        // Check if date selector is null or event name is empty
        // Create the event and add it to the CalendarHandler
        if(dateSelector.getValue() == null || fieldNewEvent.getText().isEmpty()) return;
        CalendarHandler.addEvent(new Event(dateSelector.getValue().toString().substring(2), fieldNewEvent.getText(), fieldDescription.getText()));

        // Clear the text input fields
        fieldNewEvent.clear();
        fieldDescription.clear();

        // Display the newly created event to the event list (This can be null)
        // If it is null, (so we created an event when we dont have anything selected), then nothing happens
        // Reload the calendar after
        displayEventList(Utility.getEventsOnDate(currentSelectedDate));
        loadCalendar();
    }

    /**
     * Deletes an event after using the delete button
     *
     * Requires: Nothing
     * Modifies: lbEventName, lbDescription, btnDelete, CalendarHandler.events
     * Effects: Deletes the event from CalendarHandler.events
     *          Resets name and description + disables delete button
     */
    public void deleteEvent(ActionEvent actionEvent) {

        // Delete selected event and reload calendar
        CalendarHandler.deleteEvent(listEvents.getSelectionModel().getSelectedItem());
        loadCalendar();

        // Disable and reset all labels + buttons because deleting the event deletes it from the listView
        lbEventName.setText("NO SELECTED EVENT");
        lbDescription.setText("NO DESCRIPTION");
        btnDelete.setDisable(true);

        // Reload the event list
        displayEventList(Utility.getEventsOnDate(currentSelectedDate));
    }

    /**
     * Loads an event from the event list when clicked
     *
     * Requires: Nothing
     * Modifies: listEvents, lbEventName, lbDescription, btnDelete
     * Effects: Sets Name, Description corresponding to the selected event, enables delete button
     */
    public void loadEvent(MouseEvent mouseEvent) {

        // Gets the current selected event in the event list and returns if it is null
        Event e = listEvents.getSelectionModel().getSelectedItem();
        if(e == null) return;

        // Sets all the labels and enables the delete button
        lbEventName.setText(e.name.toUpperCase());
        lbDescription.setText(e.description);
        btnDelete.setDisable(false);
    }

    /**
     * Checks if the user selected an event in the event list
     *
     * Requires: Nothing
     * Modifies: lbEventName, lbDescription, btnDelete
     * Effects: Checks if the user selected an event from event list
     *          If yes, return
     *          If no, change text and disable the delete button
     */
    public void checkSelectedEvent(MouseEvent mouseEvent) {

        // When clicking anywhere on the calendar, check if the selection is null
        // This stops the event from continuing to display after another date has been selected
        if(listEvents.getSelectionModel().getSelectedItem() == null) {
            lbEventName.setText("NO SELECTED EVENT");
            lbDescription.setText("NO DESCRIPTION");
            btnDelete.setDisable(true);
        }
    }

    /**
     * Load from saved data when you use the calendar (1 time only)
     *
     * Requires: Nothing
     * Modifies: loaded
     * Effects: Renders calendar with saved data
     */
    public void loadFromData(MouseEvent mouseEvent) {

        // If data is already loaded, return, as we only want to load once on start
        // If this is not checked, events will duplicated everytime we put our mouse over the calendar
        if(loaded) return;

        // As soon as we move our mouse over the calendar, it will load from data
        // Reload the calendar after
        loaded = true;
        IOHandler.readIn();
        loadCalendar();
    }
}
