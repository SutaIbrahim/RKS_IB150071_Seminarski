package com.example.ibrahim.servisinfo_ib150071;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.AutentifikacijaLoginPostVM;
import com.example.ibrahim.servisinfo_ib150071.data.AutentifikacijaResultVM;
import com.example.ibrahim.servisinfo_ib150071.data.Global;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.username_input2);
        txtPassword = findViewById(R.id.password_input);

        Button RegistracijaBtn=(Button) findViewById(R.id.regBtn);

        RegistracijaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });



    Button PrijavaBtn=(Button)findViewById(R.id.loginBtn);




    PrijavaBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            do_btn_Click();

            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }


    });
    }

        private void do_btn_Click () {

        if(validacija()) {


            String strUsername = txtUsername.getText().toString();
            String strPassword = txtPassword.getText().toString();


            AutentifikacijaLoginPostVM model = new AutentifikacijaLoginPostVM(strUsername, strPassword);

            MyApiRequest.get(this, "api/Autentifikacija/LoginCheck/" + strUsername + "/" + strPassword, new MyRunnable<AutentifikacijaResultVM>() {
                @Override
                public void run(AutentifikacijaResultVM x) {
                    checkLogin(x);
                }
            });
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle("Greska");
            alertDialog.setMessage("Polje username i/ili password je prazno");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        }

    private boolean validacija() {
        if(txtUsername.getText().toString().isEmpty())
            return false;
        if(txtPassword.getText().toString().isEmpty())
            return false;
        return true;
    }


    private void checkLogin(AutentifikacijaResultVM x) {
        if (x==null)
        {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan username/password", Snackbar.LENGTH_LONG).show();
        }
        else
        {
          // MySession.setKorisnik(x);
            Global.prijavljeniKlijent=x;

           startActivity(new Intent(this, MainActivity.class));

        }
    }

    @Override
    public void onBackPressed() {
        //ako se klijent odjavi da se ne moze vratiti u aplikaciju vec pritiskom na back izlazi se iz aplikacije

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }
}
