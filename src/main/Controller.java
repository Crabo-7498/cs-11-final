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

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                int daysFromNow = j + (7 * i);
                System.out.println(daysFromNow);

                String date = getNextDate(daysFromNow);

                VBox cell = new VBox();
                Label dateLabel = new Label(date);
                dateLabel.setTranslateY(-21);
                dateLabel.setTranslateX(5);

                cell.getChildren().set(0, dateLabel);


                gpaneCalendar.add(cell, j, i);
            }
        }
    }

    private String getNextDate(int days) {
        final SimpleDateFormat format = new SimpleDateFormat("MM/dd");
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
