package com.example.ibrahim.servisinfo_ib150071;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ibrahim.servisinfo_ib150071.Fragments.DetaljiKompanijeDialogFragment;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.Util.Util;
import com.example.ibrahim.servisinfo_ib150071.data.Global;
import com.example.ibrahim.servisinfo_ib150071.data.GradoviResultVM;
import com.example.ibrahim.servisinfo_ib150071.data.KompanijePregledVM;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private KompanijePregledVM podaci;
    private GradoviResultVM gradovi;
    private Spinner gradSpinner;
    private BaseAdapter adapter;
    private ListView lvKompanije;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbar);

  /*      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// moje->


        //postavljanje username-a
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
       TextView t= (TextView) headerView.findViewById(R.id.korisnikTxt);
        t.setText(Global.prijavljeniKlijent.Ime +" " +Global.prijavljeniKlijent.Prezime);





        lvKompanije=(ListView) findViewById(R.id.rv);



        gradSpinner=findViewById(R.id.gradIzbor);
        postaviDimenzijeSpinnera();


        gradSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                doSpinnerItemClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        popuniGradoveTask();
        popuniPodatkeTask("0");


        lvKompanije.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//pohrani izabranu kompaniju
                 String selected = ((TextView) view.findViewById(R.id.KompanijaIDHiddenTxt)).getText().toString();
                Global.izabranaKompanijaID=selected;

                do_item_Click();
            }
        });

    }

    private void do_item_Click() {

        Util.otvoriFragmentKaoDijalog(this, DetaljiKompanijeDialogFragment.newInstance());

        //Util.otvoriFragmentKaoReplace(this,R.id.mjestoFragment,DetaljiKompanijeFragment.newInstance());

    }

    private void doSpinnerItemClick() {

        popuniPodatkeTask(gradSpinner.getSelectedItem().toString());

    }

    private void postaviDimenzijeSpinnera() {

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(gradSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(550);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        gradSpinner.setDropDownWidth(550);

    }

    //func

    private void popuniGradoveTask() {

        gradSpinner.setClickable(true);
        MyApiRequest.get(this,"/api/gradovi", new MyRunnable<GradoviResultVM>() {
            @Override
            public void run(GradoviResultVM x) {
                gradovi = x;
                popuniGradove();
            }
        });
    }

    private void popuniGradove(){

        List<String> gradList = new ArrayList<String>();
        gradList.add("---");

        for(GradoviResultVM.Row x :gradovi.rows){
            gradList.add(x.Naziv);
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gradList);
        gradSpinner.setAdapter(adapter2);

    }



    private void popuniPodatkeTask( String grad) {

        if(grad!="0") {
            MyApiRequest.get(this, "/api/kompanije/GetKompanije/"+ grad, new MyRunnable<KompanijePregledVM>() {
                @Override
                public void run(KompanijePregledVM x) {
                    podaci = x; //postavljeno kao field radi    adapter.notifyDataSetChanged(); za brisanje posiljke iz ListView
                    popuniPodatke();
                }
            });
        }
        else {
            MyApiRequest.get(this, "/api/kompanije/GetKompanije/---", new MyRunnable<KompanijePregledVM>() {
                @Override
                public void run(KompanijePregledVM x) {
                    podaci = x; //postavljeno kao field radi    adapter.notifyDataSetChanged(); za brisanje posiljke iz ListView
                    popuniPodatke();
                }
            });
        }
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
        public View getView(int position, View view, final ViewGroup parent) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item, parent, false);
            }
            TextView txtFirstLine = view.findViewById(R.id.NazivKompanijeTxt);
            TextView txtSecondLine = view.findViewById(R.id.AdresaKompanijeTxt);
            TextView txtKID = view.findViewById(R.id.KompanijaIDHiddenTxt);

            KompanijePregledVM.Row x = podaci.rows.get(position);

            txtFirstLine.setText(x.Naziv);
            txtSecondLine.setText(x.Adresa + ", " + x.Grad);
            txtKID.setText( String.valueOf(x.KompanijaID));




            return view;
        }
    };

    lvKompanije.setAdapter(adapter);






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            //izadji iz app

            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pocetna) {
            // Handle the camera action
        } else if (id == R.id.nav_upiti) {
            startActivity(new Intent(MainActivity.this,UpitiActivity.class));
        } else if (id == R.id.nav_postavke) {
            startActivity(new Intent(MainActivity.this,PostavkeActivity.class));

        } else if (id == R.id.nav_odjava) {
            Global.prijavljeniKlijent=null;
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
