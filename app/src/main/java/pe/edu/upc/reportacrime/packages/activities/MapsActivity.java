package pe.edu.upc.reportacrime.packages.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private double[] latitudes;
    private double[] longitudes;
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        else zoom = 17;
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
