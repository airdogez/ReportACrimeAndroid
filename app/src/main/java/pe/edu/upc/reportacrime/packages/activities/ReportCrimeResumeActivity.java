package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.reportacrime.R;
import pe.edu.upc.reportacrime.packages.helpers.UrlHelper;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrimeResumeActivity extends AppCompatActivity{


    Button btnSendReport;
    TextView titleTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView addressTextView;
    TextView tagsTextView;

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

        addressTextView = (TextView) findViewById(R.id.addressTextView);
        addressTextView.setText(bundle.getString("address"));

        tagsTextView = (TextView) findViewById(R.id.tagsTextView);
        tagsTextView.setText(bundle.getString("tag_list"));

        btnSendReport = (Button)findViewById(R.id.buttonSend);
        btnSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bundle.getString("name");
                String description = bundle.getString("description");
                int user_id = bundle.getInt("user_id");
                int district_id = bundle.getInt("district_id");
                int category_id = bundle.getInt("category_id");
                String address = bundle.getString("address");
                String tag_list = bundle.getString("tag_list");
                try {
                    sendReport(name, description, user_id, district_id, category_id, address, tag_list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendReport(String name, String description, int user_id, int district_id, int category_id, String address, String  tag_list) throws JSONException {
  String jsonBody = "{\"crime\":{\"name\":\"" + name
                            + "\",\"description\":\"" + description
                            + "\",\"user_id\":\"" + user_id
                            + "\",\"district_id\":\"" + district_id
                            + "\",\"category_id\":\"" + category_id
                            + "\",\"address\":\"" + address
                            + "\",\"tag_list\":\"" + tag_list
                            + "\",\"status_id\":\"1\"}}";
        JSONObject request = new JSONObject(jsonBody);
        request.toString();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, UrlHelper.CREATE_CRIME_REPORT_URL, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(ReportCrimeResumeActivity.this, MainMenuActivity.class);
                startActivity(intent);
                Toast.makeText(ReportCrimeResumeActivity.this, "Crime registered successfully", Toast.LENGTH_LONG).show();
                finish();
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

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
