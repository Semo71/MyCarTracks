package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class events extends AppCompatActivity {

    private static  final String TAG ="events";
    private TextView mDisplayDate, mDisplayDate2, tv2;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        mDisplayDate=(TextView) findViewById(R.id.tv1);
        mDisplayDate2 =(TextView) findViewById(R.id.tv2);

        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar ca = Calendar.getInstance();
                int year2 = ca.get(Calendar.YEAR);
                int month2 = ca.get(Calendar.MONTH);
                int day2 = ca.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        events.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year2,month2,day2);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year2, int month2, int day2) {
                month2 = month2 +1;
                Log.d(TAG,"OnDateSet: mm/dd/yyy: " + month2 +"/" + day2 +"/" + year2);
                String date2 = month2 + "/" + day2 + "/" + year2;
                mDisplayDate2.setText(date2);
                mDisplayDate2.setBackgroundResource(0);
            }
        };



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        events.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1; // اول شهر 0 واخر شهر 11
                Log.d(TAG, "onDateSet: mm/dd/yyy: " +month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
                mDisplayDate.setBackgroundResource(0);
            }
        };

    }




    public void button7(View v){ //كود الانتقال للصفحه التاليه
        Intent intent = new Intent(events.this,service.class);
        startActivity(intent);
        finish();
    }
    public void button9 (View v){ // كود الانتقال
        Intent intent = new Intent(events.this,upcoming.class);
        startActivity(intent);
        finish();
    }
    public void button3 (View v){ // كود الانتقال
        Intent intent = new Intent(events.this,service.class);
        startActivity(intent);
        finish();
    }
}
