package com.example.ibrahim.servisinfo_ib150071;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.KlijentPostVM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        ime = (EditText) findViewById(R.id.ImeTxt);
        prezime = (EditText) findViewById(R.id.PrezimeTxt);
        email = (EditText) findViewById(R.id.EmailTxt);
        telefon = (EditText) findViewById(R.id.telefonTxt);
        username = (EditText) findViewById(R.id.KorisnickoImeTxt);
        pass = (EditText) findViewById(R.id.passwordTxt);

        Button regBtn = (Button) findViewById(R.id.registrujSeBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btn_Click();

            }
        });


    }

    private void do_btn_Click() {

        if (validacija()) {

            KlijentPostVM model = new KlijentPostVM();
            model.Ime = ime.getText().toString();
            model.Prezime = prezime.getText().toString();
            model.Email = email.getText().toString();
            model.Telefon = telefon.getText().toString();
            model.KorisickoIme = username.getText().toString();
            model.LozinkaSalt = pass.getText().toString();
            model.Adresa = "x";
            model.GradID = 1;// incijalizacija, nema opcije biranja grada

            MyApiRequest.post(this, "api/Klijenti", model, new MyRunnable<KlijentPostVM>() {
                @Override
                public void run(KlijentPostVM x) {
                    Toast.makeText(RegistrationActivity.this, "Uspjesna registracija", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }

    }

    private boolean validacija() {

        AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
        alertDialog.setTitle("Greška");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        if (ime.getText().toString().length() < 3) {
            alertDialog.setMessage("Ime treba sadrzavati vise od 2 karaktera");
            alertDialog.show();

            return false;
        }

        if (prezime.getText().toString().length() < 3) {
            alertDialog.setMessage("Prezime treba sadrzavati vise od 2 karaktera");
            alertDialog.show();

            return false;
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() == false) {
            alertDialog.setMessage("Email nije u ispravnom formatu");
            alertDialog.show();

            return false;
        }

        if (telefon.getText().toString().length() < 6) {
            alertDialog.setMessage("Telefon treba sadrzavati najmanje 6 brojeva");
            alertDialog.show();

            return false;
        }

        if (username.getText().toString().length() < 4) {
            alertDialog.setMessage("Korisnicko ime treba sadrzavati vise od 3 karaktera");
            alertDialog.show();

            return false;
        }

        if (true) { // pass
            Pattern pattern;
            Matcher matcher;

            final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$";

            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(pass.getText().toString());

            if (matcher.matches() == false) {
                alertDialog.setMessage("Password treba sadrzavati minimalno 6 karaktera: \n -najmanje jedan broj \n -kombinaciju malih/velikih slova");
                alertDialog.show();

                return false;
            }

        }

        return true;
    }
}
