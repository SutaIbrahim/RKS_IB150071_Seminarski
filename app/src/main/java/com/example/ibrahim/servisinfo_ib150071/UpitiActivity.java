package com.example.ibrahim.servisinfo_ib150071;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.Global;
import com.example.ibrahim.servisinfo_ib150071.data.KompanijePregledVM;
import com.example.ibrahim.servisinfo_ib150071.data.UpitiResultVM;

public class UpitiActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private ListView rv;
    private UpitiResultVM podaci;
    private BaseAdapter adapter;
    private ListView lvUpiti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upiti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpitiActivity.this, DodajUpitActivity.class));

               *//* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //postavljanje username-a
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView t = (TextView) headerView.findViewById(R.id.korisnikTxt);
        t.setText(Global.prijavljeniKlijent.Ime + " " + Global.prijavljeniKlijent.Prezime);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                int id = item.getItemId();

                if (id == R.id.nav_pocetna) {
                    startActivity(new Intent(UpitiActivity.this, MainActivity.class));
                } else if (id == R.id.nav_upiti) {

                } else if (id == R.id.nav_postavke) {
                    startActivity(new Intent(UpitiActivity.this, PostavkeActivity.class));

                } else if (id == R.id.nav_odjava) {
                    izbrisiToken();
                    startActivity(new Intent(UpitiActivity.this, LoginActivity.class));

                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        lvUpiti = (ListView) findViewById(R.id.lvUpiti);

        lvUpiti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //pohrani izabrani upit
                String selected = ((TextView) view.findViewById(R.id.UpitIDHiddenTxt)).getText().toString();
                Global.izabraniUpitID = Integer.parseInt(selected);

                do_item_Click();
            }
        });

        popuniPodatkeTask();

    }

    private void do_item_Click() {
        startActivity(new Intent(this, ZaFragmentActivity.class));
    }

    private void popuniPodatkeTask() {

        MyApiRequest.get(this, "/api/upiti/getUpitiByKlijentID/" + String.valueOf(Global.prijavljeniKlijent.KlijentID), new MyRunnable<UpitiResultVM>() {
            @Override
            public void run(UpitiResultVM x) {
                podaci = x;
                popuniPodatke();
            }
        });
    }


    private void popuniPodatke() {

        if (podaci.rows.size() > 0) {

            adapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return podaci.rows.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View view, ViewGroup parent) {

                    if (view == null) {
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.itemupiti, parent, false);
                    }
                    TextView txtFirstLine = view.findViewById(R.id.NaslovUpitaTxt);
                    TextView txtSecondLine = view.findViewById(R.id.MarkaUredjajaTxt);
                    TextView txtHidden = view.findViewById(R.id.UpitIDHiddenTxt);

                    UpitiResultVM.Row x = podaci.rows.get(position);

                    txtFirstLine.setText(x.Naslov);
                    txtSecondLine.setText(x.MarkaUredjaja);
                    txtHidden.setText(String.valueOf(x.UpitID));

                    return view;
                }
            };

            lvUpiti.setAdapter(adapter);
        } else {
            TextView u = (TextView) findViewById(R.id.textView3);
            u.setText("Nije pronadjen nijedan upit");
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(UpitiActivity.this, MainActivity.class));
        }
    }

    private void izbrisiToken() {
        MyApiRequest.delete(this, "api/autentifikacija/Logout", new MyRunnable<KompanijePregledVM>() {
            @Override
            public void run(KompanijePregledVM x) {
                //
            }
        });
    }


}

