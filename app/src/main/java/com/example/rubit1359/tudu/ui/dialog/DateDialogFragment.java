package com.example.rubit1359.tudu.ui.dialog;


//**

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.rubit1359.tudu.ui.AddTaskActivity;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by rubit1359 on 9/30/2016.
 */

public class DateDialogFragment extends DialogFragment implements DatePickerDialog
        .OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    public String getMonth(int fakeMonth) {
        return new DateFormatSymbols().getShortMonths()[fakeMonth];
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int
            day) {
        String dueYear = String.valueOf(year);
        String dueMonth = getMonth(month);
        String dueDate = String.valueOf(day);

        AddTaskActivity activity = (AddTaskActivity) getActivity();
        activity.updateDueDate(dueYear, dueMonth, dueDate);
    }
}


