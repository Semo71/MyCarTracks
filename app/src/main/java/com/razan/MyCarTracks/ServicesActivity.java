package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.razan.MyCarTracks.Alarm.AddReminderActivity;
import com.razan.MyCarTracks.Alarm.SensorDataManager;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsKeys;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    private Spinner serviceBySensorSpinner, serviceByDateSpinner;
    private Button mBackButton;
    private Context mContext;
    private SensorDialog mSensorDialog;
    public static final int FILTER = 1;
    public static final int SPEED_LIMIT = 2;
    SensorDataModel mSensorDataModel;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceBySensorSpinner = findViewById(R.id.spinner1);
        serviceByDateSpinner = findViewById(R.id.spinner2);
        mBackButton = findViewById(R.id.backButton);
        mContext = ServicesActivity.this;
        mSensorDialog = new SensorDialog(mContext);
        mSensorDataModel = new SensorDataModel();
        prepareServiceBySensorSpinner();
        prepareServiceByDateSpinner();


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /** Setting both Spinners Selection to 0 **/
        serviceByDateSpinner.setSelection(0);
    }

    /**
     * Preparing Service By Sensor Spinner
     **/
    private void prepareServiceBySensorSpinner() {
        ArrayList<String> serviceBySensorList;
        ArrayAdapter<String> serviceBySensorAdapter;

        serviceBySensorList = new ArrayList<>();
        serviceBySensorList.add("Choose Service");
        serviceBySensorList.add("Filters");
        serviceBySensorList.add("Speed Limit");


        serviceBySensorAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, serviceBySensorList);
        serviceBySensorSpinner.setAdapter(serviceBySensorAdapter);

        serviceBySensorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        break;
                    case 1://Filters
                        showDialog(FILTER, "Filter", "Enter Filter in Km");
                        break;
                    case 2://Speed Limit
                        showDialog(SPEED_LIMIT, "Speed Limit", "Enter Speed Limit in Km");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    /**
     * Preparing Service By Date Spinner
     **/
    private void prepareServiceByDateSpinner() {
        ArrayAdapter<String> serviceByDateAdapter;
        final ArrayList<String> serviceByDateList;

        serviceByDateList = new ArrayList<>();
        serviceByDateList.add("Choose Service");
        serviceByDateList.add(SharedPrefsKeys.CAR_INSURANCE);
        serviceByDateList.add(SharedPrefsKeys.VEHICLE_INSPECTION);
        serviceByDateList.add(SharedPrefsKeys.AC_GAS);
        serviceByDateList.add(SharedPrefsKeys.BELTS);
        serviceByDateList.add(SharedPrefsKeys.SPARK_PLUGS);
        serviceByDateList.add(SharedPrefsKeys.WHEELS);
        serviceByDateList.add(SharedPrefsKeys.BATTERY);

        serviceByDateAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, serviceByDateList);
        serviceByDateSpinner.setAdapter(serviceByDateAdapter);

        serviceByDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    sendToAddReminderActivity(serviceByDateList.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    /**
     * Send to AddReminderActivity with Service Name
     **/
    private void sendToAddReminderActivity(String serviceName) {
        Intent intent = new Intent(mContext, AddReminderActivity.class);
        intent.putExtra("ServiceName", serviceName);
        startActivity(intent);
    }


    //Show custom dialog from SensorDialog class
    private void showDialog(final int type, String title, String hint) {
        serviceBySensorSpinner.setSelection(0);
        mSensorDialog.showDialog(type,title, hint, new SensorDialog.OnClicks() {
            @Override
            public void onSaveClickListener(boolean activated, String editTextValue) {
                mSensorDataModel.setActivated(activated);
                mSensorDataModel.setEnteredValue(Integer.parseInt(editTextValue));
                if (type == FILTER)
                    SensorDataManager.setFilter(mSensorDataModel);
                else if (type == SPEED_LIMIT)
                    SensorDataManager.setSpeedLimit(mSensorDataModel);

            }
        });
    }


}
