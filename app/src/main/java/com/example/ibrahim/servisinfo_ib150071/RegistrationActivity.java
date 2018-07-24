package com.example.ibrahim.servisinfo_ib150071;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



         Button regBtn=(Button) findViewById(R.id.registrujSeBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(RegistrationActivity.this,"Uspjesna registracija",Toast.LENGTH_SHORT).show();
                finish();
                //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });



    }
}
