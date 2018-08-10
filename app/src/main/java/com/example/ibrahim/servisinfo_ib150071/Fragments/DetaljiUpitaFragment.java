package com.example.ibrahim.servisinfo_ib150071.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.R;
import com.example.ibrahim.servisinfo_ib150071.data.UpitiVM;


public class DetaljiUpitaFragment extends Fragment {

    UpitiVM u;

    TextView naslov;
    TextView datum;
    TextView kompanija;
    TextView uredjaj;
    TextView opis;
    ImageView img;
    Bitmap slika;
    TextView slikaLbl;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int upitID;


    public DetaljiUpitaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetaljiUpitaFragment newInstance(int param1) {
        DetaljiUpitaFragment fragment = new DetaljiUpitaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            upitID = (int) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_detalji_upita, container, false);



        naslov=(TextView) view.findViewById(R.id.NazivUTxt);
        datum=(TextView) view.findViewById(R.id.datumUTxt);
        kompanija=(TextView) view.findViewById(R.id.kompanijaUTxt);
        uredjaj=(TextView) view.findViewById(R.id.uredjaUTxt);
        opis=(TextView) view.findViewById(R.id.opisKvaraTxt);
        img=(ImageView) view.findViewById(R.id.slikaKvaraIMG);
        slikaLbl=(TextView) view.findViewById(R.id.slikaLabel);


        popuniPodatkeTask();











        return view;
    }

    private void popuniPodatkeTask() {

        MyApiRequest.get(getActivity(), "/api/upiti/GetUpitByID/" + String.valueOf(upitID), new MyRunnable<UpitiVM>() {
            @Override
            public void run(UpitiVM x) {
                u = x; //postavljeno kao field radi    adapter.notifyDataSetChanged(); za brisanje posiljke iz ListView
                popuniPodatke();
            }
        });


    }

    private void popuniPodatke() {

        naslov.setText(u.Naslov);
        datum.setText(u.Datum.toString());
        kompanija.setText(u.Kompanija);
        uredjaj.setText(u.MarkaUredjaja);
        opis.setText(u.OpisKvara);


        if(StringToBitMap(u.EncodedImage) !=null){
            img.setImageBitmap(StringToBitMap(u.EncodedImage));
        }
        else{
            img.setImageBitmap(StringToBitMap(u.EncodedImage));
            slikaLbl.setText("");
        }

        // image_view.setImageBitmap(selectedImage); //pregled izabrane slike




    }

    public Bitmap StringToBitMap(String strng){
        try{
            byte [] encodeByte= Base64.decode(strng,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}
