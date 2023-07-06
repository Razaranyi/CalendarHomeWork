package com.example.calendar2;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class CalendarLogic {
    private Calendar c;
    private HashMap<Date,String> events;

    public CalendarLogic() {
        c = new GregorianCalendar();
        c.set(2022,Calendar.DECEMBER,1);
        c.setLenient(false);
        events = new HashMap<Date,String>();
    }

    public void insertEvent(Date key, String event){

        events.put(key,event);
    }
    public String getEvent(Date key){

        return events.get(key);
    }

    public void setYear(int year){
        c.set(Calendar.YEAR,year);
    }
    public void setMonth(int month){
        c.set(Calendar.MONTH,month - 1);
    }
    public void setDay(int day){
        c.set(Calendar.DAY_OF_MONTH, day);
    }
    public int getDayOfWeek(){
        return c.get(Calendar.DAY_OF_WEEK) -1 ;
    }
    public int getDaysInMonth(){

        YearMonth yearMonth = YearMonth.of(c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1);
        return yearMonth.lengthOfMonth();
    }
    public int getYear(){
        return c.get(Calendar.YEAR);
    }
    public int getMonth(){
        return c.get(Calendar.MONTH);
    }
    public int getDay(){
        return c.get(Calendar.DAY_OF_MONTH);
    }
    public Date getDate(){
        return c.getTime();
    }

}


