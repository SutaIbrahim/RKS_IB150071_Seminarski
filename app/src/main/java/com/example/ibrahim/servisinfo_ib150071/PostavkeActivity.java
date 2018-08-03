package com.example.ibrahim.servisinfo_ib150071;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.AutentifikacijaResultVM;
import com.example.ibrahim.servisinfo_ib150071.data.Global;

public class PostavkeActivity extends AppCompatActivity {
    TextView tel;
    TextView email;
    TextView user;
    Button SpasiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postavke);

        //

        Incijalizacija();
        SpasiBtn=(Button) findViewById(R.id.spasiProfilBtn);

        SpasiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btn_clicl();
            }
        });


    }

    private void do_btn_clicl() {

        Global.prijavljeniKlijent.KorisickoIme=user.getText().toString();
Global.prijavljeniKlijent.Email=email.getText().toString();
        Global.prijavljeniKlijent.Telefon=tel.getText().toString();
        MyApiRequest.put(this, "api/Klijenti/",Global.prijavljeniKlijent, new MyRunnable<AutentifikacijaResultVM>() {
            @Override
            public void run(AutentifikacijaResultVM x) {
                AlertDialog alertDialog = new AlertDialog.Builder(PostavkeActivity.this).create();
                alertDialog.setTitle("Info");
                alertDialog.setMessage("Uspjesno ste uredili profil");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Incijalizacija(); // refresh
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });


    }


    private void Incijalizacija() {
        tel = (TextView)findViewById(R.id.telefonKTxt);
        email = (TextView)findViewById(R.id.emailKTxt);
        user = (TextView)findViewById(R.id.adresaKTxt);

        tel.setText( Global.prijavljeniKlijent.Telefon);
        email.setText(Global.prijavljeniKlijent.Email);
        user.setText(Global.prijavljeniKlijent.KorisickoIme);
    }




}
