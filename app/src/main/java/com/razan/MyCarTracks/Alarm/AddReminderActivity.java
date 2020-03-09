package com.razan.MyCarTracks.Alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.razan.MyCarTracks.R;

import java.net.URI;
import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity {

    private static final String TAG = "AddReminderActivity";

    private TextView mServiceNameTxtV;
    private TextView mChosenDateTxtV;
    private TextView mChosenTimeTxtV;
    private SwitchCompat mReminderSwitch, mActivateAlarmSwitch;
    private Button mSaveBtn;
    private LinearLayout mServiceDateLL;
    private LinearLayout mServiceTimeLL;

    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;
    private Context mContext;
    private Calendar mCalendar;
    private ServicesModel mServicesModel;

    private Integer year, month, day, hour, minute;
    private String serviceName;
    private String chosenDate;
    private boolean hour24;


    /**
     * To Activate back arrow in Action Toolbar
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        mContext = AddReminderActivity.this;
        //Getting Service Name from the past activity
        serviceName = getIntent().getStringExtra("ServiceName");
        mServicesModel = ServicesDateManager.getServicesModel(serviceName);
        //mURI=getIntent().getData();
        //Log.d("URI",mURI.toString());
        Log.d("myapp", Log.getStackTraceString(new Exception()));


        connectUiComponent();
        handleCalender();
        updatingUIViews();

        mServiceDateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        mServiceTimeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        mActivateAlarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mServicesModel.setAlarmActivated(isChecked);
            }
        });

        mReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mServicesModel.setReminderActivated(isChecked);
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnAlarm();
            }
        });

        setOnDateSetListener();
        setOnTimeSetListener();
    }

    //Connecting UI Views with id from xml file
    private void connectUiComponent() {
        mServiceNameTxtV = findViewById(R.id.serviceNameTxtV);
        mChosenDateTxtV = findViewById(R.id.chosenDateTxtV);
        mChosenTimeTxtV = findViewById(R.id.chosenTimeTxtV);
        mReminderSwitch = findViewById(R.id.reminderSwitch);
        mActivateAlarmSwitch = findViewById(R.id.activateAlarmSwitch);
        mSaveBtn = findViewById(R.id.saveBtn);
        mServiceDateLL = findViewById(R.id.serviceDateLL);
        mServiceTimeLL = findViewById(R.id.serviceTimeLL);
    }

    //Handling Calender Initialization for 2 cases (Service Model != null and Service Model == null)
    private void handleCalender() {
        if (mServicesModel.getCalendar() != null) {
            mCalendar = mServicesModel.getCalendar();
        } else {
            mCalendar = Calendar.getInstance();
        }
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH);
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(Calendar.MINUTE);
        hour24 = false;
    }

    //Updating UI views in case Service Model != null
    private void updatingUIViews() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Add Reminder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mServiceNameTxtV.setText(serviceName);
        if (mServicesModel.getCalendar() != null) {
            updateDateTxtV();
            updateTimeTxtV();
            mActivateAlarmSwitch.setChecked(mServicesModel.isAlarmActivated());
            mReminderSwitch.setChecked(mServicesModel.isReminderActivated());
        }
    }

    //Initialize and show Date Picker Dialog
    private void showDatePickerDialog() {
        DatePickerDialog mDatePickerDialog = new DatePickerDialog(
                mContext,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mOnDateSetListener,
                year, month, day);

        assert mDatePickerDialog.getWindow() != null;
        mDatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDatePickerDialog.show();

    }

    //Initialize and show Time Picker Dialog
    private void showTimePickerDialog() {
        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                mContext,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mOnTimeSetListener,
                hour, minute, hour24
        );

        assert mTimePickerDialog.getWindow() != null;
        mTimePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePickerDialog.show();
    }

    //Assigning year, month and day variables when pressing ok button in date picker dialog,
    //then calling updateDateTxtV() method
    private void setOnDateSetListener() {
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                AddReminderActivity.this.year = year;
                AddReminderActivity.this.month = month;
                AddReminderActivity.this.day = day;

                updateDateTxtV();
            }
        };
    }

    //Assigning hour and minute variables when pressing ok button in time dialog,
    //then calling updateTimeTxtV() method
    private void setOnTimeSetListener() {
        mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                hour = hourOfDay;
                AddReminderActivity.this.minute = minute;
                updateTimeTxtV();
            }
        };
    }

    //Handling date format and Updating the Date Text View
    private void updateDateTxtV() {
        int monthInt = month + 1;
        chosenDate = day + "/" + monthInt + "/" + year;
        mChosenDateTxtV.setText(chosenDate);
    }

    //Handling AM and PM format and Updating the Time Text View
    private void updateTimeTxtV() {
        String am_pm;

        if (mCalendar.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (mCalendar.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";
        else
            am_pm = "";

        String hourOfDay12HourFormat = (mCalendar.get(Calendar.HOUR) == 0) ? "12" : mCalendar.get(Calendar.HOUR) + "";
        String time;
        if (minute < 10) {
            time = hourOfDay12HourFormat + ":" + "0" + minute + " " + am_pm;

        } else {
            time = hourOfDay12HourFormat + ":" + minute + " " + am_pm;
        }

        mChosenTimeTxtV.setText(time);
    }


    //Setting an alarm and saving service in SharedPreference
    private void setAnAlarm() {
        if (year != null && hour != null) {
            PendingIntent mPendingIntent;
            AlarmManager mAlarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mContext, AlarmReceiver.class);
            intent.putExtra("title", serviceName);
            intent.putExtra("message", chosenDate);

            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, day);
            mCalendar.set(Calendar.HOUR_OF_DAY, hour);
            mCalendar.set(Calendar.MINUTE, minute);


            if (mServicesModel.isAlarmActivated()) {
                intent.putExtra("AlarmNumber", mServicesModel.getFirstRequestID());
                mPendingIntent = PendingIntent.getBroadcast(mContext, mServicesModel.getFirstRequestID(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                assert mAlarmManager != null;
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

            } else {
                mPendingIntent = PendingIntent.getBroadcast(mContext, mServicesModel.getFirstRequestID(), intent, 0);
                assert mAlarmManager != null;
                mAlarmManager.cancel(mPendingIntent);
            }

            if (mServicesModel.isReminderActivated()) {
                Calendar mReminderCalender = Calendar.getInstance();
                mReminderCalender.set(Calendar.YEAR, year);
                mReminderCalender.set(Calendar.MONTH, month);
                mReminderCalender.set(Calendar.DAY_OF_MONTH, day);
                mReminderCalender.set(Calendar.HOUR_OF_DAY, hour);
                mReminderCalender.set(Calendar.MINUTE, minute);
                mReminderCalender.add(Calendar.DAY_OF_MONTH, -2); //Goes to 2 days earlier

                Log.d(TAG, "mReminderCalender: " + new Gson().toJson(mReminderCalender));

                intent.putExtra("AlarmNumber", mServicesModel.getSecondRequestID());

                mPendingIntent = PendingIntent.getBroadcast(mContext, mServicesModel.getSecondRequestID(), intent, 0);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, mReminderCalender.getTimeInMillis(), mPendingIntent);
            } else {
                mPendingIntent = PendingIntent.getBroadcast(mContext, mServicesModel.getSecondRequestID(), intent, 0);
                mAlarmManager.cancel(mPendingIntent);
            }

            mServicesModel.setCalendar(mCalendar);
            ServicesDateManager.setServicesModel(mServicesModel, serviceName);

            Toast.makeText(mContext, "Alarm was set successfully", Toast.LENGTH_LONG).show();
            finish();


        } else {
            Toast.makeText(AddReminderActivity.this, "Please Pick a date and time", Toast.LENGTH_SHORT).show();
        }

    }
}
