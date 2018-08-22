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

        Util.otvoriFragmentKaoReplace(this, R.id.mjestoFragment2, DetaljiUpitaFragment.newInstance(Global.izabraniUpitID));

    }

}
