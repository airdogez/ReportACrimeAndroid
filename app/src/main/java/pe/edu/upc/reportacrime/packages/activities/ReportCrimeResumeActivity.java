package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.reportacrime.R;
import pe.edu.upc.reportacrime.packages.helpers.UrlHelper;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrimeResumeActivity extends Activity {


    Button btnSendReport;
    TextView titleTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView longitudeTextView;
    TextView latitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_a_crime_resume);

        final Bundle bundle = getIntent().getExtras();

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(bundle.getString("name"));

        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(bundle.getString("description"));

        categoryTextView = (TextView) findViewById(R.id.categoryTextView);
        categoryTextView.setText(bundle.getString("category"));

        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        latitudeTextView.setText(String.valueOf(bundle.getDouble("latitude")));

        longitudeTextView = (TextView) findViewById(R.id.longitudeTextView);
        longitudeTextView.setText(String.valueOf(bundle.getDouble("longitude")));


        btnSendReport = (Button)findViewById(R.id.buttonSend);
        btnSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bundle.getString("name");
                String description = bundle.getString("description");
                int user_id = bundle.getInt("user_id");
                int district_id = bundle.getInt("district_id");
                int category_id = bundle.getInt("category_id");
                double latitude = bundle.getDouble("latitude");
                double longitude = bundle.getDouble("longitude");
                try {
                    sendReport(name, description, user_id, district_id, category_id, latitude, longitude);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendReport(String name, String description, int user_id, int district_id, int category_id, double longitude, double latitude) throws JSONException {
  String jsonBody = "{\"crime\":{\"name\":\"" + name
                            + "\",\"description\":\"" + description
                            + "\",\"user_id\":\"" + user_id
                            + "\",\"district_id\":\"" + district_id
                            + "\",\"category_id\":\"" + category_id
                            + "\",\"longitude\":\"" + longitude
                            + "\",\"latitude\":\"" + latitude
                            + "\",\"status_id\":\"1\"}}";
        JSONObject request = new JSONObject(jsonBody);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, UrlHelper.CREATE_CRIME_REPORT_URL, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(ReportCrimeResumeActivity.this, "Crime registered successfully", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
        finish();
    }
}
