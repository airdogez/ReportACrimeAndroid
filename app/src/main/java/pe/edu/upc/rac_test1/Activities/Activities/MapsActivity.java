package pe.edu.upc.rac_test1.Activities.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.upc.rac_test1.R;

public class MapsActivity extends FragmentActivity {


    private String district;
    double latGetted;
    double longGetted;



    private CameraUpdate nCamera;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        district = getIntent().getStringExtra(DelictiveZones.EXTRA_ADDRESS);

        latGetted =getLocationFromAddress(district).latitude;
        longGetted =getLocationFromAddress(district).longitude;
        setUpMapIfNeeded();




        //Intent searchAddress = new  Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + district ));
        //startActivity(searchAddress);



    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                
                mMap.setMyLocationEnabled(true); // Activa MyPosition y el boton
                setUpMap();
            }
        }
    }




    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        /*
        mMap.addMarker(new MarkerOptions().position(new LatLng(-12.1040537, -76.9630775)).title("UPC")//Setea Una posicion en el gmaps y con Titulo
                //  .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)) //cambia el icono por defecto de gmaps
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.radiation))   // Setea el icono desde un marker propio
                .snippet("Centro de Estudios Universitarios")); //A�ade una descripcion

                */
        setMarker(new LatLng(-12.1040537, -76.9630775), "UPC", "Alerta de Bomba", 0.5F,0.1F,0.1F , R.drawable.bomb );
        setMarker(new LatLng(-12.1048693,-76.9614882), "ESAN", "Alerta de Explosion", 0.5F,0.1F,0.1F , R.drawable.explosion );
        setMarker(new LatLng(-12.1046385,-76.9633457), "Academia de Cultura Japonesa", "Alerta de fuego", 0.5F,0.1F,0.1F , R.drawable.fire );
        setMarker(new LatLng(-12.1044707,-76.9639572), "PAD", "Alerta de Radiacion", 0.5F,0.1F,0.1F , R.drawable.radiation );
        setMarker(new LatLng(-12.1049847,-76.9635602), "Av. Primavera", "Alerta de Asalto", 0.5F,0.1F,0.1F , R.drawable.gun );



        nCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(latGetted,longGetted),15); //actualiza la posicion de la camara
        mMap.animateCamera(nCamera); // anima la camara a esa posicion
        //nCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(-12.1040537,-76.9630775),15); //actualiza la posicion de la camara
        //mMap.animateCamera(nCamera); // anima la camara a esa posicion
    }

    private void setMarker(LatLng position, String title, String info, float opacity, float dimension1, float dimension2,int icon){
        //Agregar un marcador para indicar sitios de interes.
        mMap.addMarker(new MarkerOptions()
                .position(position) //posicion del marcador
                .title(title) // Titulo al marcador
                .snippet(info) //Descripcion del mercador
                .alpha(opacity) //Opacidad del icono
                .anchor(dimension1, dimension2) //Tama�o del icono Alto-Ancho
                .icon(BitmapDescriptorFactory.fromResource(icon)));
    }
}
