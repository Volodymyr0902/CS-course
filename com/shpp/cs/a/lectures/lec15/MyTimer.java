package com.shpp.cs.a.lectures.lec15;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is done!");
            }
        };

        Calendar date = Calendar.getInstance();
        date.set(Calendar.MINUTE, 24);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        System.out.println(date.getTime());
        //timer.schedule(task, date.getTime());

        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}