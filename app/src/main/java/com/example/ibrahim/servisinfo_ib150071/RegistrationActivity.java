package com.example.ibrahim.servisinfo_ib150071;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.KlijentPostVM;

public class RegistrationActivity extends AppCompatActivity {


    private EditText ime;
    private EditText prezime;
    private EditText email;
    private EditText telefon;
    private EditText username;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        ime=(EditText)findViewById(R.id.ImeTxt) ;
        prezime=(EditText)findViewById(R.id.PrezimeTxt) ;
        email=(EditText)findViewById(R.id.EmailTxt) ;
        telefon=(EditText)findViewById(R.id.telefonTxt) ;
        username=(EditText)findViewById(R.id.KorisnickoImeTxt) ;
        pass=(EditText)findViewById(R.id.passwordTxt) ;

        Button regBtn=(Button) findViewById(R.id.registrujSeBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    do_btn_Click();


                //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });



    }

    private void do_btn_Click() {

        KlijentPostVM model=new KlijentPostVM();
        model.Ime=ime.getText().toString();
        model.Prezime=prezime.getText().toString();
        model.Email=email.getText().toString();
        model.Telefon=telefon.getText().toString();
        model.KorisickoIme=username.getText().toString();
        model.LozinkaSalt=pass.getText().toString();
        model.Adresa="Test";
        model.GradID=2;

        MyApiRequest.post(this, "api/Klijenti", model, new MyRunnable<KlijentPostVM>() {
            @Override
            public void run(KlijentPostVM x) {
                Toast.makeText(RegistrationActivity.this,"Uspjesna registracija",Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}
