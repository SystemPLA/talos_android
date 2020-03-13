package ru.systempla.talos_android.mvp.view.ui.DatePicker;

import android.app.DatePickerDialog;
import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MyDatePicker {
    String formattedDate = "";
    public MyDatePicker(Context context, TextInputEditText viewForDate) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {

            String d;
            String m;
            month++;
            if (month < 10) {
                m = "0" + ((Integer)month).toString();
            } else {
                m = ((Integer)month).toString();
            }
            if (dayOfMonth < 10){
                d = "0" + ((Integer)dayOfMonth).toString();
            } else {
                d = ((Integer)dayOfMonth).toString();
            }

            formattedDate = year + "-" + m + "-" + d;
            viewForDate.setText(formattedDate);
            viewForDate.setError(null);
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
