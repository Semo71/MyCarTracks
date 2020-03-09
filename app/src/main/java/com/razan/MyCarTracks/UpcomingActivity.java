package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razan.MyCarTracks.Alarm.ServicesDateManager;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsKeys;

import java.util.Calendar;

public class UpcomingActivity extends AppCompatActivity {

    private Button mBackButton;
    private TextView mCarInsuranceTxtV;
    private TextView mVehicleInspectionTxtV;
    private TextView mAcGasTxtV;
    private TextView mBeltsTxtV;
    private TextView mSparkPlugsTxtV;
    private TextView mWheelsTxtV;
    private TextView mBatteryTxtV;
    private TextView mFiltersTxtV;
    private TextView mSpeedLimitsTxtV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        connectUiComponent();
        updatingUIViews();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //Connecting UI Views with id from xml file
    private void connectUiComponent() {
        mBackButton =findViewById(R.id.backButton);
        mCarInsuranceTxtV =findViewById(R.id.carInsuranceTxtV);
        mVehicleInspectionTxtV =findViewById(R.id.vehicleInspectionTxtV);
        mAcGasTxtV =findViewById(R.id.acGasTxtV);
        mBeltsTxtV =findViewById(R.id.beltsTxtV);
        mSparkPlugsTxtV =findViewById(R.id.sparkPlugsTxtV);
        mWheelsTxtV =findViewById(R.id.wheelsTxtV);
        mFiltersTxtV =findViewById(R.id.filtersTxtV);
        mSpeedLimitsTxtV =findViewById(R.id.speedLimitsTxtV);
        mBatteryTxtV =findViewById(R.id.batteryTxtV);
    }

    //Updating UI views in case Service Model for each service != null
    private void updatingUIViews() {
        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.CAR_INSURANCE).getCalendar() != null)
            mCarInsuranceTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.CAR_INSURANCE).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.VEHICLE_INSPECTION).getCalendar() != null)
            mVehicleInspectionTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.VEHICLE_INSPECTION).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.AC_GAS).getCalendar() != null)
            mAcGasTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.AC_GAS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.BELTS).getCalendar() != null)
            mBeltsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.BELTS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.SPARK_PLUGS).getCalendar() != null)
            mSparkPlugsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.SPARK_PLUGS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.WHEELS).getCalendar() != null)
            mWheelsTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.WHEELS).getCalendar()));

        if (ServicesDateManager.getServicesModel(SharedPrefsKeys.BATTERY).getCalendar() != null)
            mBatteryTxtV.setText(getDateFormat(ServicesDateManager.getServicesModel(SharedPrefsKeys.BATTERY).getCalendar()));

    }

    //Returning a date format string from calender parameter
    private String getDateFormat(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH)+1;
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + month
                + "/" + calendar.get(Calendar.YEAR);
    }

}
