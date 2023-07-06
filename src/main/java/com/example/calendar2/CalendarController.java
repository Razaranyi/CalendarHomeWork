package com.example.calendar2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.Objects;


public class CalendarController {

    @FXML
    private ComboBox<Integer> monthBtn;

    @FXML
    private ComboBox<Integer> yearBtn;

    @FXML
    private TextArea addEvents;

    @FXML
    private TextArea events;

    @FXML
    private GridPane grid;


    private ObservableList<Integer> years;
    private  ObservableList<Integer> months;
    private CalendarLogic ca;
    private Button[] btns;

    private final  int FIRST_YEAR = 1990;
    private final int LAST_YEAR = 2030;
    private final int DEFAULT_MONTH = 11;
    private final int DEFAULT_YEAR = 2022;
    private final int MONTHS_IN_YEAR = 12;
    private final int SIZE = 31, COLUMNS = 7, ROWS = 7;





    public void initialize(){
        ca = new CalendarLogic();
        years = FXCollections.observableArrayList();
        months = FXCollections.observableArrayList();
        btns = new Button[SIZE];
        initializeComBox();
        setDefaultPage();
        generateBtns();

    }

    @FXML
    void monthBtnClicked(ActionEvent event) {
        removeBtns();
        ca.setMonth(monthBtn.getValue());
        generateBtns();

    }

    @FXML
    void yearBtnClicked(ActionEvent event) {
        removeBtns();
        ca.setYear(yearBtn.getValue());
        generateBtns();
    }
    @FXML
    void enterEvent(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            ca.insertEvent(ca.getDate(),addEvents.getText());
            events.setText(ca.getEvent(ca.getDate()));
        }
    }
    @FXML
    void OkPressed(ActionEvent event) {
        ca.insertEvent(ca.getDate(),addEvents.getText());
        events.setText(ca.getEvent(ca.getDate()));
    }



    private void setDefaultPage(){
        monthBtn.setValue(months.get(DEFAULT_MONTH));
        yearBtn.setValue(years.get(DEFAULT_YEAR-FIRST_YEAR));
        ca.setYear(DEFAULT_YEAR);
        ca.setMonth(DEFAULT_MONTH + 1);
        addEvents.setPromptText("Add event to " + ca.getDay() + "/" + (ca.getMonth() + 1) + "/" + ca.getYear());


    }
    private void initializeComBox(){
        for (int i = FIRST_YEAR; i<=LAST_YEAR; i++){
            years.add(i);
        }
        for (int i = 0; i< MONTHS_IN_YEAR; i++){
            months.add(i+1);
        }
        monthBtn.setItems(months);
        yearBtn.setItems(years);
    }

    private void generateBtns() {
        int dayInMonth = ca.getDaysInMonth();
        int firstDay = ca.getDayOfWeek();
        for (int i = firstDay; i < dayInMonth + firstDay; i++) {
            btns[i - firstDay] = new Button(1+i-firstDay + "");
            btns[i-firstDay].setPrefSize(grid.getPrefWidth() / COLUMNS, grid.getPrefHeight() / ROWS);
            grid.add(btns[i-firstDay], i % COLUMNS, i / COLUMNS);
            btns[i-firstDay].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleBtnAction(actionEvent);
                }
            });
        }
    }
    private void removeBtns(){
        for (int i = 0; i < SIZE; i++ ){
            grid.getChildren().remove(btns[i]);
        }

    }
    private void handleBtnAction(ActionEvent event){

        Button tmp = (Button) event.getSource();
        addEvents.clear();
        events.clear();
        ca.setDay(Integer.parseInt(tmp.getText()));
        String currentEvent = ca.getEvent(ca.getDate());
        if (currentEvent == null || currentEvent.equals("")) {
            addEvents.setPromptText("Add events to " + ca.getDay() + "/" + (ca.getMonth() + 1) + "/" + ca.getYear());
        }
        else addEvents.setText(currentEvent);
        events.setText(ca.getEvent(ca.getDate()));
    }


}
