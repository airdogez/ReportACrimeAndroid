package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pe.edu.upc.reportacrime.packages.adapters.CategoriesAdapter;
import pe.edu.upc.reportacrime.packages.adapters.DistrictsAdapter;
import pe.edu.upc.reportacrime.packages.models.Category;
import pe.edu.upc.reportacrime.R;
import pe.edu.upc.reportacrime.packages.models.District;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrimeActivity extends AppCompatActivity implements LocationListener{

    private Uri fileUri; //El Uri es una clase de android
    private static final int MEDIA_TYPE_IMAGE = 1;//La camara del dispositivo puede tomar foto y grabar video
    private static final int CAPTURE_IMAGE_REQUEST_CODE = 100;//Pediremos a la camara que capture una foto

    private DistrictsAdapter mDistrictsAdapter;
    private CategoriesAdapter mCategoryAdapter;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText addressEditText;
    private EditText tagsEditText;

    private Spinner categorySpinner;
    private Spinner districtSpinner;

    private Button reportButton;
    private ImageButton cameraButton;

    private double longitude;
    private double latitude;

    private Geocoder geocoder;

    private String address;


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

            if(location!=null) {
                onLocationChanged(location);
                addressEditText = (EditText) findViewById(R.id.addressEditText);

                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert addresses != null;

                address = addresses.get(0).getAddressLine(0); //Street
                //String dis = addresses.get(0).getAddressLine(1); //Postal code
                //String dis2 = addresses.get(0).getAddressLine(2); //Department

                addressEditText.setText(address);
            }else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }

        titleEditText = (EditText)findViewById(R.id.titleEditText);
        descriptionEditText = (EditText)findViewById(R.id.descriptionEditText);
        tagsEditText = (EditText)findViewById(R.id.tagsEditText);

        mDistrictsAdapter = new DistrictsAdapter(this, R.layout.spinner, SplashScreenActivity.getDistricts());
        districtSpinner = (Spinner)findViewById(R.id.districtsSpinner);
        districtSpinner.setAdapter(mDistrictsAdapter);

        mCategoryAdapter = new CategoriesAdapter(this, R.layout.spinner, SplashScreenActivity.getCategories());
        categorySpinner = (Spinner)findViewById(R.id.categoriesSpinner);
        categorySpinner.setAdapter(mCategoryAdapter);

        cameraButton = (ImageButton) findViewById(R.id.cameraImageButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(i);
            }
        });

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
                bundle.putString("tag_list", tagsEditText.getText().toString());
                District district = (District) districtSpinner.getSelectedItem();
                bundle.putString("address",addressEditText.getText()+ ", " + district.getName());
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