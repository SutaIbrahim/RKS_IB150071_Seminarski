package com.example.ibrahim.servisinfo_ib150071;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class UpitiActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private List<Person> persons;
    private RecyclerView rv;

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

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Naslov upita", "Model mobilnog uredjaja"));
        persons.add(new Person("Popravak displeya", "Samsung Galaxy s4"));
        persons.add(new Person("Zamjena baterije", "Sony xperia Z2"));
        persons.add(new Person("Nedefinisan problem", "text text text text text text text text"));
        persons.add(new Person("Utor za punjenje neispravan", "text text text text text text text text"));
        persons.add(new Person("Upit 1", "text text text text text text text text"));
        persons.add(new Person("Upit 1", "text text text text text text text text"));
        persons.add(new Person("Upit 1", "text text text text text text text text"));
        persons.add(new Person("Upit 1", "text text text text text text text text"));
        persons.add(new Person("Upit 1", "text text text text text text text texttext text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text text texttext text text text text text texttext text text text text text text text"));

    }

    private void initializeAdapter(){
        RVAdapterUpiti adapter = new RVAdapterUpiti(persons);
        rv.setAdapter(adapter);
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

