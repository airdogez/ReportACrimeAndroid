package pe.edu.upc.reportacrime.Activities.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import pe.edu.upc.reportacrime.R;

/**
 * Created by Andres R on 06/07/2015.
 */
public class CrimeItemActivity extends AppCompatActivity{
    TextView titleTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView districtiTextView;
    TextView statusTextView;
    TextView addressTextView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_item);

        Bundle bundle = getIntent().getExtras();
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(bundle.getString("title"));
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(bundle.getString("description"));
        categoryTextView= (TextView) findViewById(R.id.categoryTextView);
        categoryTextView.setText(bundle.getString("category"));
        districtiTextView= (TextView) findViewById(R.id.districtTextView);
        districtiTextView.setText(bundle.getString("district"));
        statusTextView= (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText(bundle.getString("status"));
        addressTextView= (TextView) findViewById(R.id.addressTextView);
        addressTextView.setText(bundle.getString("address"));

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
