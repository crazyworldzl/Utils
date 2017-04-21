package com.al.utils.View;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;

/**
 * Created by ZhangLong on 2017/4/10.
 */

public class MyTimePickerDialog extends TimePickerDialog {
    private TimePicker mTimePicker;
    private OnTimeSetListener mCallback;
    private String[] strings = {"00", "30", "00", "30", "00", "30"};
    public MyTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
        this.mCallback = listener;
    }

    public MyTimePickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
        this.mCallback = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            Class<?> rClass = Class.forName("com.android.internal.R$id");
            Field timePicker = rClass.getField("timePicker");
            this.mTimePicker = (TimePicker) findViewById(timePicker.getInt(null));
            Field m = rClass.getField("minute");

            NumberPicker mMinuteSpinner = (NumberPicker) mTimePicker.findViewById(m.getInt(null));
            mMinuteSpinner.setMinValue(0);
            mMinuteSpinner.setMaxValue(strings.length - 1);

            mMinuteSpinner.setDisplayedValues(strings);
            mMinuteSpinner.setOnLongPressUpdateInterval(100);
        }catch (Exception e){

        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        if (mCallback != null && mTimePicker != null) {
            mTimePicker.clearFocus();
            mCallback.onTimeSet(mTimePicker, mTimePicker.getCurrentHour(), ((mTimePicker.getCurrentMinute() % 2) == 1 ? 30 : 0));
        }
    }
}
