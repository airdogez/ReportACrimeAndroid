package pe.edu.upc.reportacrime.packages.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.R;
import pe.edu.upc.reportacrime.packages.adapters.DistrictsAdapter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private double[] latitudes;
    private double[] longitudes;
    private ArrayList<String> titles = new ArrayList<>();

    private Spinner districtsSpinner;
    private DistrictsAdapter mDistrictsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment map = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mDistrictsAdapter = new DistrictsAdapter(this, R.layout.spinner, SplashScreenActivity.getDistricts());
        districtsSpinner = (Spinner) findViewById(R.id.districtsSpinner);
        districtsSpinner.setAdapter(mDistrictsAdapter);

        districtsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        map.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        latitudes = bundle.getDoubleArray("latitude");
        longitudes = bundle.getDoubleArray("longitude");
        titles = bundle.getStringArrayList("name");
    }

    @Override
    public void onMapReady(GoogleMap map){
        LatLng latlng;
        int zoom;
        if (titles.size() > 1) zoom = 10;
        else{
            zoom = 17;
            districtsSpinner.setVisibility(View.GONE);
        }
        for(int pos = 0; pos < titles.size(); pos++){
            double latitude = latitudes[pos];
            double longitude = longitudes[pos];
            latlng = new LatLng(latitude, longitude);
            String title = titles.get(pos);
            map.addMarker(new MarkerOptions().position(latlng).title(title));
        }
        latlng = new LatLng(latitudes[0], longitudes[0]);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
    }

}
