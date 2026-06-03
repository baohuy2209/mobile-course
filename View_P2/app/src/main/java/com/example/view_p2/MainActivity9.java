package com.example.view_p2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_p2.databinding.ActivityMain9Binding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity9 extends AppCompatActivity {
    ActivityMain9Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectEvents();
        loadData();
    }
    private void loadData(){
        SimpleDateFormat sdf =
                new SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                );

        String currentDate =
                sdf.format(new Date());

        binding.txtTimePicker.setText(currentDate);
    }
    private void connectEvents(){
        binding.btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendarDate = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                DatePickerDialog.OnDateSetListener callBack = new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendarDate.set(Calendar.YEAR, i);
                                calendarDate.set(Calendar.MONTH, i1);
                                calendarDate.set(Calendar.DAY_OF_MONTH, i2);
                                binding.txtDatePicker.setText(dateFormat.format(calendarDate.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity9.this,
                        callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        binding.btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendarTime = Calendar.getInstance();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                TimePickerDialog.OnTimeSetListener callBack = new
                        TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                calendarTime.set(Calendar.HOUR_OF_DAY, i);
                                calendarTime.set(Calendar.MINUTE, i1);
                                binding.txtTimePicker.setText(timeFormat.format(calendarTime.getTime()));
                            }
                        };
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(
                                MainActivity9.this,
                                callBack,
                                calendarTime.get(Calendar.HOUR_OF_DAY),
                                calendarTime.get(Calendar.MINUTE),
                                true
                        );
                timePickerDialog.show();
            }
        });
    }
}