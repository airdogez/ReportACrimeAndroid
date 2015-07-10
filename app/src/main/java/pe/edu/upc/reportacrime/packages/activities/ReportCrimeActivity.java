package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.packages.adapters.CategoriesAdapter;
import pe.edu.upc.reportacrime.packages.adapters.DistrictsAdapter;
import pe.edu.upc.reportacrime.packages.models.Crime;
import pe.edu.upc.reportacrime.packages.models.District;
import pe.edu.upc.reportacrime.packages.models.Category;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrimeActivity extends AppCompatActivity implements LocationListener{

    private Crime crime;
    private DistrictsAdapter mDistrictsAdapter;

    private CategoriesAdapter mCategoryAdapter;


    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner categorySpinner;
    private Spinner districtSpinner;
    private Button reportButton;

    private double longitude;
    private double latitude;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_a_crime);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
        Criteria criteria = new Criteria();

        String provider = lm.getBestProvider(criteria, false);

        if(provider!=null && !provider.equals("")){
            // Get the location from the given provider
            Location location = lm.getLastKnownLocation(provider);

            lm.requestLocationUpdates(provider, 20000, 1, this);

            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }

        titleEditText = (EditText)findViewById(R.id.titleEditText);
        descriptionEditText = (EditText)findViewById(R.id.descriptionEditText);

        mDistrictsAdapter = new DistrictsAdapter(this, R.layout.spinner, SplashScreenActivity.getDistricts());
        districtSpinner = (Spinner)findViewById(R.id.districtsSpinner);
        districtSpinner.setAdapter(mDistrictsAdapter);

        mCategoryAdapter = new CategoriesAdapter(this, R.layout.spinner, SplashScreenActivity.getCategories());
        categorySpinner = (Spinner)findViewById(R.id.categoriesSpinner);
        categorySpinner.setAdapter(mCategoryAdapter);

        reportButton = (Button)findViewById(R.id.buttonReport);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ReportCrimeActivity.this, ReportCrimeResumeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_id", LoginActivity.getUser().getId());
                bundle.putString("name", titleEditText.getText().toString());
                bundle.putString("description", descriptionEditText.getText().toString());
                int cat_id = (int) categorySpinner.getSelectedItemId();
                bundle.putInt("category_id", cat_id);
                Category cat = (Category) categorySpinner.getSelectedItem();
                bundle.putString("category", cat.getName());
                int dis_id = (int)districtSpinner.getSelectedItemId();
                bundle.putInt("district_id", dis_id);
                bundle.putDouble("latitude", latitude);
                bundle.putDouble("longitude", longitude);
                i.putExtras(bundle);
                v.getContext().startActivity(i);
            }
        });

    }


    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}