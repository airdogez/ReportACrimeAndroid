package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class ReportCrimeResume extends Activity {


    Button mSend;

    private String type;
    private String services;
    private String description;
    private String latitude;
    private String longitude;

    TextView mType;
    TextView mServices;
    TextView mDescription;
    TextView mLatitude;
    TextView mLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_a_crime_resume);

        type = getIntent().getStringExtra(ReportCrime.EXTRA_TYPE);
        services = getIntent().getStringExtra(ReportCrime.EXTRA_SERVICES);
        description = getIntent().getStringExtra(ReportCrime.EXTRA_DESCRIPTION);
        latitude = getIntent().getStringExtra(ReportCrime.EXTRA_LATITUDE);
        longitude = getIntent().getStringExtra(ReportCrime.EXTRA_LONGITUDE);

        mType = (TextView) findViewById(R.id.tvTypeOfCrimeR);
        mType.setText(type);

        mServices = (TextView) findViewById(R.id.tvServReqR);
        mServices.setText(services);

        mDescription = (TextView) findViewById(R.id.tvDescriptionR);
        mDescription.setText(description);

        mLatitude = (TextView) findViewById(R.id.tvLatitudeR);
        mLatitude.setText(latitude);

        mLongitude = (TextView) findViewById(R.id.tvLongitudeR);
        mLongitude.setText(longitude);


        mSend = (Button)findViewById(R.id.buttonSend);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainUser.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
                //Intent i = new Intent(ReportCrimeResume.this, .class);

                //startActivity(i);
            }
        });






    }
}
