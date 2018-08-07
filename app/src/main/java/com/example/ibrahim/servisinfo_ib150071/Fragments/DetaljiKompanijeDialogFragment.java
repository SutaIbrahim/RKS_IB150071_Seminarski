package com.example.ibrahim.servisinfo_ib150071.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibrahim.servisinfo_ib150071.DodajUpitActivity;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.R;
import com.example.ibrahim.servisinfo_ib150071.data.Global;
import com.example.ibrahim.servisinfo_ib150071.data.Kompanije;


public class DetaljiKompanijeDialogFragment extends DialogFragment {

    Kompanije k;

    Button nazadBtn;
    Button posaljiBtn;

TextView naziv;
TextView telefon;
TextView adresa;
TextView email;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DetaljiKompanijeDialogFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetaljiKompanijeDialogFragment newInstance() {
        DetaljiKompanijeDialogFragment fragment = new DetaljiKompanijeDialogFragment();
       /* Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalji_kompanije, container, false);


        popuniPodatkeTask();


        naziv=(TextView) view.findViewById(R.id.NazivKTxt);
        telefon=(TextView) view.findViewById(R.id.telefonKTxt);
        adresa=(TextView) view.findViewById(R.id.adresaKTxt);
        email=(TextView) view.findViewById(R.id.emailKTxt);





        posaljiBtn=(Button) view.findViewById(R.id.PosaljiUpitDBtn);
        nazadBtn =(Button) view.findViewById(R.id.nazadBtn);


        posaljiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DodajUpitActivity.class));
                dismiss();

            }
        });


        nazadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return view;
    }



    private void popuniPodatkeTask( ) {

            MyApiRequest.get(getActivity(), "/api/kompanije/GetKompanijaByID/" + Global.izabranaKompanijaID, new MyRunnable<Kompanije>() {
                @Override
                public void run(Kompanije x) {
                    k = x; //postavljeno kao field radi    adapter.notifyDataSetChanged(); za brisanje posiljke iz ListView
                    popuniPodatke();
                }
            });

    }

    private void popuniPodatke() {

        naziv.setText(k.Naziv);
        telefon.setText(k.Telefon);
        adresa.setText(k.Adresa);
        email.setText(k.Email);
    }
    }