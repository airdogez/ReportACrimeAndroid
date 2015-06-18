package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class SignUp extends Activity {

    Button buttonRegister;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Intent i = new Intent(SignUp.this, .class);
               // startActivity(i);
            }
        });



    }
}
