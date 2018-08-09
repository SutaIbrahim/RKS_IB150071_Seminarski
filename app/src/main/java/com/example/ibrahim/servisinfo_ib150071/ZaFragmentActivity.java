package com.example.ibrahim.servisinfo_ib150071;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ibrahim.servisinfo_ib150071.Fragments.DetaljiUpitaFragment;
import com.example.ibrahim.servisinfo_ib150071.Util.Util;
import com.example.ibrahim.servisinfo_ib150071.data.Global;

public class ZaFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_za_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Util.otvoriFragmentKaoReplace(this,R.id.mjestoFragment2, DetaljiUpitaFragment.newInstance(Global.izabraniUpitID));

       // startActivity(new Intent(this, MainActivity.class));

    }

}
