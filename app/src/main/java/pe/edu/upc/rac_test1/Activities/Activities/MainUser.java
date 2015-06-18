package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class MainUser extends Activity {

    ImageButton imageButtonReport;
    ImageButton imageButtonZones;
    ImageButton imageButtonHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        imageButtonReport = (ImageButton)findViewById(R.id.imageButtonReport);
        imageButtonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUser.this, ReportCrime.class);
                startActivity(i);
            }
        });

        imageButtonZones = (ImageButton)findViewById(R.id.imageButtonZones);
        imageButtonZones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUser.this, DelictiveZones.class);
                startActivity(i);
            }
        });

        imageButtonHistory = (ImageButton)findViewById(R.id.imageButtonHistory);
        imageButtonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUser.this, History.class);
                startActivity(i);
            }
        });



    }
}
