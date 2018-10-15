package com.project.school.regenity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    //handle reminders

    Map<String,List<String>> reminders;
    List<String> notifications;


    DataHandler(){
        this.reminders = reminders = new HashMap<>();
        this.notifications = new ArrayList<>();
    }

    public void addNotification(String notification){
        this.notifications.add(notification);
    }

    public void addReminder(String date,String reminder){
        List<String> day_reminders = this.reminders.get(date);
        day_reminders.add(reminder);
    }

    public List<String> getNotifications(Context context){
        if(this.notifications == null){
            readFromFileNotifications(context);
        }
        return this.notifications;
    }

    public List<String> getReminders(String date,Context context){
        if(reminders.get(date) == null){
            readFromFileReminders(date,context);
        }
        List<String> day_reminders = this.reminders.get(date);
        return day_reminders;
    }

    private void writeToFileNotifications(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("Notifications.txt", Context.MODE_PRIVATE));
            for(String notification : notifications){
                outputStreamWriter.write(notification+"\n");
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void readFromFileNotifications(Context context) {
        try {
            InputStream inputStream = context.openFileInput("Notifications.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ((receiveString = bufferedReader.readLine()) != null) {
                    notifications.add(receiveString);
                }
                inputStream.close();

            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    private void writeToFileReminders(Context context){
        for(String date: reminders.keySet()){
            writeToFileRemindersPerDate(date,context);
        }
    }
    private void writeToFileRemindersPerDate (String date,Context context){
        String filename = date+".txt";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            for(String reminder : reminders.get(date)){
                outputStreamWriter.write(reminder+"\n");
            }
            outputStreamWriter.close();
        } catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
        }
    }

        private void readFromFileReminders (String date, Context context){
            String filename = date+".txt";
            List<String> day_reminder = reminders.get(date);
            day_reminder = new ArrayList<>();
            try {
                InputStream inputStream = context.openFileInput(filename);

                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        reminders.get(date).add(receiveString);
                    }
                    inputStream.close();
                }
            } catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
            } catch (IOException e) {
                Log.e("login activity", "Can not read file: " + e.toString());
            }

        }
    }

