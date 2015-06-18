package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrime extends Activity {

    TextView mTxtLatitude;
    TextView mTxtLongitude;
    Button mButtonReport;
    Spinner sp;

    EditText type;
    String services;
    EditText description;


    public static final String EXTRA_TYPE = "com.coderefer.switchactivityandroid.type";
    public static final String EXTRA_SERVICES = "com.coderefer.switchactivityandroid.services";
    public static final String EXTRA_DESCRIPTION = "com.coderefer.switchactivityandroid.description";
    public static final String EXTRA_LATITUDE = "com.coderefer.switchactivityandroid.latitude";
    public static final String EXTRA_LONGITUDE = "com.coderefer.switchactivityandroid.longitude";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_a_crime);

        mTxtLatitude =(TextView)findViewById(R.id.tvLatitude);
        mTxtLongitude =(TextView)findViewById(R.id.tvLongitude);
        type = (EditText)findViewById(R.id.etTypeOfCrime);
        description = (EditText)findViewById(R.id.etDescription);
        LocationManager milocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MiLocationListener milocListener = new MiLocationListener();

        milocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, milocListener);

        mButtonReport = (Button)findViewById(R.id.buttonReport);
        mButtonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportCrime.this, ReportCrimeResume.class);

                String ETtype = type.getText().toString();
                String ETdescription = description.getText().toString();
                String ETlatitude = mTxtLatitude.getText().toString();
                String ETlongitude = mTxtLongitude.getText().toString();

                i.putExtra(EXTRA_TYPE, ETtype);
                i.putExtra(EXTRA_SERVICES, services);
                i.putExtra(EXTRA_DESCRIPTION, ETdescription);
                i.putExtra(EXTRA_LATITUDE, ETlatitude);
                i.putExtra(EXTRA_LONGITUDE, ETlongitude);

                startActivity(i);
            }
        });


        sp = (Spinner)findViewById(R.id.spinnerServices);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.ServicesRequired, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                services = parentView.getItemAtPosition(position).toString();
                Toast.makeText(parentView.getContext(), "Has Seleccionado "+ parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public class MiLocationListener implements LocationListener {
        public void onLocationChanged(Location loc) {
            //loc.getLatitude();

            //loc.getLongitude();

            mTxtLatitude.setText(loc.getLatitude()+"");

            mTxtLongitude.setText(loc.getLongitude()+"");

            //String coordenadas = (“Mis coordenadas son: ” + “Latitud = ” + loc.getLatitude() + “Longitud = ” + loc.getLongitude());
            //Toast.makeText( getApplicationContext(),coordenadas,Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(),"GPS Desactivado", Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String provider) {
            Toast.makeText( getApplicationContext(),"GPS Activado",Toast.LENGTH_SHORT ).show();

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}