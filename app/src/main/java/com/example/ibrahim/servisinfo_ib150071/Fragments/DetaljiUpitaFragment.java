package com.example.ibrahim.servisinfo_ib150071.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.R;
import com.example.ibrahim.servisinfo_ib150071.UpitiActivity;
import com.example.ibrahim.servisinfo_ib150071.data.UpitiVM;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DetaljiUpitaFragment extends Fragment {

    UpitiVM u;

    TextView naslov;
    TextView datum;
    TextView kompanija;
    TextView uredjaj;
    TextView opis;
    ImageView img;
    TextView slikaLbl;
    Bitmap bm;
    Button izbrisiButton;


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
        View view = inflater.inflate(R.layout.fragment_detalji_upita, container, false);


        naslov = (TextView) view.findViewById(R.id.NazivUTxt);
        datum = (TextView) view.findViewById(R.id.datumUTxt);
        kompanija = (TextView) view.findViewById(R.id.kompanijaUTxt);
        uredjaj = (TextView) view.findViewById(R.id.uredjaUTxt);
        opis = (TextView) view.findViewById(R.id.opisKvaraTxt);
        img = (ImageView) view.findViewById(R.id.slikaKvaraIMG);
        slikaLbl = (TextView) view.findViewById(R.id.slikaLabel);

        izbrisiButton = (Button) view.findViewById(R.id.izbrisiUpitBtn);

        popuniPodatkeTask();

        img.buildDrawingCache();
        bm = img.getDrawingCache();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preuzmiSlikuNaUredjaj();
            }
        });

        izbrisiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PotvrdaBrisanja();
            }
        });


        return view;
    }

    private void PotvrdaBrisanja() {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Upozorenje");
        alertDialog.setMessage("Jeste li sigurni da zelite izbrisati upit?");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DA",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        izbrisiUpit();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Odustani",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            alertDialog.show();
    }

    private void izbrisiUpit() {

        Calendar trenutno=Calendar.getInstance();
        Calendar datumUpita=new GregorianCalendar();

        datumUpita.setTime(u.Datum);
        trenutno.add(Calendar.HOUR,-1);

        if(trenutno.after(datumUpita))
        {
            Toast.makeText(getActivity(), "Samo upiti kreirani u zadnjih 1h se mogu izbrisati", Toast.LENGTH_SHORT).show();
        }
          else
        {
            MyApiRequest.delete(getActivity(), "/api/upiti/" + String.valueOf(upitID), new MyRunnable<UpitiVM>() {
                @Override
                public void run(UpitiVM x) {
                    Toast.makeText(getActivity(), "Upit uspjesno izbrisan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), UpitiActivity.class));
                }
            });
        }
    }

    private void popuniPodatkeTask() {

        MyApiRequest.get(getActivity(), "/api/upiti/GetUpitByID/" + String.valueOf(upitID), new MyRunnable<UpitiVM>() {
            @Override
            public void run(UpitiVM x) {
                u = x;
                popuniPodatke();
            }
        });

    }

    private void popuniPodatke() {

        naslov.setText(u.Naslov);
        datum.setText(u.Datum.toLocaleString().toString());
        kompanija.setText(u.Kompanija);
        uredjaj.setText(u.MarkaUredjaja);
        opis.setText(u.OpisKvara);

        if (StringToBitMap(u.EncodedImage) != null) {
            img.setImageBitmap(StringToBitMap(u.EncodedImage));
        } else {
            img.setImageBitmap(StringToBitMap(u.EncodedImage));
            slikaLbl.setText("");
        }

    }

    public Bitmap StringToBitMap(String strng) {
        try {
            byte[] encodeByte = Base64.decode(strng, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    private void preuzmiSlikuNaUredjaj() {
        /*OutputStream fOut = null;
        Uri outputFileUri;
        try {
            File root = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "/media/external/images/" + File.separator);
            root.mkdirs();
            File sdImageMainDirectory = new File(root, "ServisInfo.jpg");
            outputFileUri = Uri.fromFile(sdImageMainDirectory);
            fOut = new FileOutputStream(sdImageMainDirectory);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Trenutno nije moguce preuzeti sliku", Toast.LENGTH_SHORT).show();
        }
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
        }*/
    }



}
