package pe.edu.upc.reportacrime.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import pe.edu.upc.reportacrime.Activities.Models.Crime;
import pe.edu.upc.reportacrime.Activities.Models.User;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class MainMenuActivity extends Activity {

    ImageButton imageButtonReport;
    ImageButton imageButtonZones;
    ImageButton imageButtonHistory;
    User user;
    private static ArrayList<Crime> crimes = new ArrayList<>();
    private static String CRIMES_SEARCH_URL = "http://mobdev-aqws3.c9.io/api/v1/crimes?user_id=";

    public static ArrayList<Crime> getCrimes(){
        return crimes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        user = LoginActivity.getUser();
        imageButtonReport = (ImageButton)findViewById(R.id.imageButtonReport);
        imageButtonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, ReportCrime.class);
                startActivity(i);
            }
        });

        imageButtonZones = (ImageButton)findViewById(R.id.imageButtonZones);
        imageButtonZones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, DelictiveZones.class);
                startActivity(i);
            }
        });

        imageButtonHistory = (ImageButton)findViewById(R.id.imageButtonHistory);
        imageButtonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = null;
                try {
                    searchString = URLEncoder.encode(String.valueOf(user.getId()), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                searchCrimes(CRIMES_SEARCH_URL + searchString);
            }
        });
    }

    public void searchCrimes(String searchString){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, searchString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    crimes.clear();
                    JSONArray resultsArray = response.getJSONArray("crimes");
                    int limit = resultsArray.length() > 10 ? 10 : resultsArray.length();

                    for (int pos = 0; pos < limit; pos++){
                        JSONObject result = resultsArray.getJSONObject(pos);
                        String title = result.getString("name");
                        String description = result.getString("description");

                        JSONObject categoryObject = result.getJSONObject("category");
                        String category = categoryObject.getString("name");

                        JSONObject districtObject = result.getJSONObject("district");
                        String district= categoryObject.getString("name");

                        JSONObject statusObject = result.getJSONObject("status");
                        String status = categoryObject.getString("name");

                        double latitude = result.getDouble("latitude");
                        double longitude = result.getDouble("longitude");
                        String address = result.getString("address");

                        Crime crime = new Crime(title, description, user.getFullName(), district, category, status, latitude, longitude, address);
                        crimes.add(crime);
                    }
                    if( crimes.size() > 0 ){
                        Intent intent = new Intent(MainMenuActivity.this, CrimeHistoryActivity.class);
                        startActivity(intent);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
