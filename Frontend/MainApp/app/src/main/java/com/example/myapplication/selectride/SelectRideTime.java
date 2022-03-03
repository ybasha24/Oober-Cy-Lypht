package com.example.myapplication.selectride;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class SelectRideTime extends AppCompatActivity {

    private static String time;
    private static String date;
    private static TextView timeTV;
    private static TextView dateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ride_time);
        dateTV = findViewById(R.id.dateSelectedTV);
        timeTV = findViewById(R.id.timeSelectedTV);
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hour, int minute) {
            String h = hour > 10 ? String.valueOf(hour) : "0" + hour;
            String m = minute > 10 ? String.valueOf(minute) : "0" + minute;
            SelectRideTime.time = h + ":" + m;
            SelectRideTime.timeTV.setText(SelectRideTime.time);
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String m = month > 10 ? String.valueOf(month) : "0" + month;
            String d = day > 10 ? String.valueOf(day) : "0" + day;
            SelectRideTime.date = year + "-" + m + "-" + d;
            SelectRideTime.dateTV.setText(SelectRideTime.date);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTime(View v){
        TextView t = findViewById(R.id.timeTV);
        String dateTimeString = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTimeString, formatter);
        t.setText(dateTimeObject.toString());
    }

    public void selectStartLocation(View v){
        Intent i = new Intent(this, SelectRideStart.class);
        startActivity(i);
    }

}