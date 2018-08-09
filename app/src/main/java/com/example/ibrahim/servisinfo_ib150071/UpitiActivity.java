package com.example.ibrahim.servisinfo_ib150071;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import com.example.ibrahim.servisinfo_ib150071.data.UpitiResultVM;

import java.util.List;

public class UpitiActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private List<Person> persons;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpitiActivity.this, DodajUpitActivity.class));

               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                int id = item.getItemId();

                if (id == R.id.nav_pocetna) {
                    startActivity(new Intent(UpitiActivity.this, MainActivity.class));
                } else if (id == R.id.nav_upiti) {

                } else if (id == R.id.nav_postavke) {

                } else if (id == R.id.nav_odjava) {
                    finish();
                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


   /*     LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);*/


        lvUpiti=(ListView) findViewById(R.id.lvUpiti);

lvUpiti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //pohrani izabrani upit
        String selected = ((TextView) view.findViewById(R.id.UpitIDHiddenTxt)).getText().toString();
        Global.izabraniUpitID= Integer.parseInt(selected);

        do_item_Click();
    }
});

        popuniPodatkeTask();





    }

    private void do_item_Click() {
/*        AlertDialog alertDialog = new AlertDialog.Builder(UpitiActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/

        startActivity(new Intent(this, ZaFragmentActivity.class));

    }

    private void popuniPodatkeTask() {

        MyApiRequest.get(this, "/api/upiti/getUpitiByKlijentID/" + String.valueOf(Global.prijavljeniKlijent.KlijentID), new MyRunnable<UpitiResultVM>() {
            @Override
            public void run(UpitiResultVM x) {
                podaci = x; //postavljeno kao field radi    adapter.notifyDataSetChanged();
                popuniPodatke();
            }
        });
    }



    private void popuniPodatke() {

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

    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }

