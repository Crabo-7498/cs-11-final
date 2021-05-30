package main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import structures.CalendarView;
import structures.CalendarHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    public Label lbTitle;
    public GridPane gpaneCalendar;
    public Pagination pgnCalendars;
    public Button btnFwrd;
    public Button btnPrev;
    public Button btnCreateCalendar;
    public TextField fieldNewCalendar;

    private void loadCalendar() {
        //System.out.println(pgnCalendars.getCurrentPageIndex());

        gpaneCalendar.getChildren().clear();
        String today = getNextDate(0);
        String currentMonth = today.substring(0, 2);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                int daysFromFDate = j + (7 * i);
                String date = getNextDate(daysFromFDate - Integer.parseInt(today.substring(2)) + 1);

                // Create Individual cells inside GridPane for easier formatting and permanent gridlines
                VBox cell = new VBox();

                // Remove "0" at start of date
                Label dateLabel;
                if(date.charAt(2) == '0')
                    dateLabel = new Label(date.substring(3));
                else
                    dateLabel = new Label(date.substring(2));

                // Move Date Label to correct position
                dateLabel.setTranslateX(5);
                dateLabel.setTranslateY(2);

                // Change font color if date is not in current month
                if(!date.substring(0, 2).equals(currentMonth))
                    dateLabel.setId("dateLabelOther");
                else
                    dateLabel.setId("dateLabel");

                cell.setId("cell");

                cell.getChildren().add(0, dateLabel);


                gpaneCalendar.add(cell, j, i);
            }
        }
    }

    private String getNextDate(int days) {
        final SimpleDateFormat format = new SimpleDateFormat("MMdd");
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format.format(calendar.getTime());
    }


    public void createCalendar() {
        if(fieldNewCalendar.getText().isEmpty()) return; // TODO: Add Error Message
        CalendarHandler.addCalendar(new CalendarView(fieldNewCalendar.getText()));
        loadCalendar();
    }
}
