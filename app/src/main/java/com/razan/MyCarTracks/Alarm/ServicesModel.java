package com.razan.MyCarTracks.Alarm;

import java.util.Calendar;

public class ServicesModel {

    private Calendar mCalendar;
    private boolean alarmActivated;
    private boolean reminderActivated;
    private int firstRequestID;
    private int secondRequestID;


    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    public void setAlarmActivated(boolean alarmActivated) {
        this.alarmActivated = alarmActivated;
    }

    public boolean isReminderActivated() {
        return reminderActivated;
    }

    public void setReminderActivated(boolean reminderActivated) {
        this.reminderActivated = reminderActivated;
    }

    public int getFirstRequestID() {
        if (firstRequestID == 0){
            setFirstRequestID();
        }
        return firstRequestID;
    }

    public void setFirstRequestID() {
        firstRequestID = (int) System.currentTimeMillis();
    }

    public int getSecondRequestID() {
        if (secondRequestID == 0){
            setSecondRequestID();
        }
        return secondRequestID;
    }

    public void setSecondRequestID() {
        secondRequestID = (int) System.currentTimeMillis()+1;
    }



}
