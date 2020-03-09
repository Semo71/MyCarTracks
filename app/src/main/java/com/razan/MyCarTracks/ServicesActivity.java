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
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsKeys;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    Spinner serviceBySensorSpinner, serviceByDateSpinner;
    private Button mBackButton;
    private Context mContext;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceBySensorSpinner = findViewById(R.id.spinner1);
        serviceByDateSpinner = findViewById(R.id.spinner2);
        mBackButton = findViewById(R.id.backButton);
        mContext = ServicesActivity.this;
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
        serviceBySensorSpinner.setSelection(0);
    }

    /** Preparing Service By Sensor Spinner **/
    private void prepareServiceBySensorSpinner() {
        ArrayList<String> serviceBySensorList;
        ArrayAdapter<String> serviceBySensorAdapter;

        serviceBySensorList = new ArrayList<>();
        serviceBySensorList.add("Filters");
        serviceBySensorList.add("Speed Limit");


        serviceBySensorAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, serviceBySensorList);
        serviceBySensorSpinner.setAdapter(serviceBySensorAdapter);

        serviceBySensorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                switch (i) {
                    case 0://Filters
                        //Do Something
                        break;
                    case 1://Speed Limit
                        //Do Something

                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    /** Preparing Service By Date Spinner **/
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

                if (i!=0){
                    sendToAddReminderActivity(serviceByDateList.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    /** Send to AddReminderActivity with Service Name **/
    private void sendToAddReminderActivity(String serviceName) {
        Intent intent = new Intent(mContext, AddReminderActivity.class);
        intent.putExtra("ServiceName", serviceName);
        startActivity(intent);
    }

}
