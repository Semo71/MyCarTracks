package com.razan.MyCarTracks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class service extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    TextView text1;

    ArrayList<String> arrayList_1;
    ArrayAdapter<String> arrayAdapter_1;
    ArrayList<String> arrayList_2;
    ArrayAdapter<String> arrayAdapter_2;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);



        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);

        arrayList_1= new ArrayList<>();
        arrayList_1.add("Filters");
        arrayList_1.add("Speed Limit");

        arrayAdapter_1 = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_1);
        spinner1.setAdapter(arrayAdapter_1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item =adapterView.getItemAtPosition(i).toString();

                Toast.makeText(adapterView.getContext(),"Selected"+ item, Toast.LENGTH_SHORT).show();
                if (adapterView.getItemAtPosition(i).equals("F"))
                {
                    // nothing to do

                }
                else
                {
                    String item1 =adapterView.getItemAtPosition(i).toString();

                    Toast.makeText(adapterView.getContext(),"Selected"+ item1, Toast.LENGTH_SHORT).show();

                    if (adapterView.getItemAtPosition(i).equals("Speed Limit"))
                    {
                        Intent intent = new Intent(service.this,upcoming.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        arrayList_2 = new ArrayList<>();
        arrayList_2.add("Car Insurance");
        arrayList_2.add("Vehicle Inspection");
        arrayList_2.add("Ac Gas");
        arrayList_2.add("Belts");
        arrayList_2.add("Spark Plugs");
        arrayList_2.add("Wheels");
        arrayList_2.add("Battery");

        arrayAdapter_2 = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_2);
        spinner2.setAdapter(arrayAdapter_2);




      //  Spinner spinner1 = findViewById(R.id.spinner1); // Spinner code
      //  spinner1.setOnItemSelectedListener(this);

       // Spinner spinner2 = findViewById(R.id.spinner2);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
          //    this,
            //    R.array.serviceD,
              // android.R.layout.simple_spinner_item
      //  );
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner2.setAdapter(adapter);
        //spinner2.setOnItemSelectedListener(this);


    }



    public void button6(View v){ // كود الانتقال
        Intent intent = new Intent(service.this, upcoming.class);
        startActivity(intent);
        finish();
    }
    public void button5(View v){ // كود الانتفال
        Intent intent = new Intent(service.this,events.class);
        startActivity(intent);
        finish();
    }
    public void button10 (View v){ // كود التنقل
        Intent intent = new Intent(service.this,events.class);
        startActivity(intent);
        finish();
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
