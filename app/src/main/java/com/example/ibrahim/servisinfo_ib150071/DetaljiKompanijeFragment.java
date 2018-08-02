package com.example.ibrahim.servisinfo_ib150071;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DetaljiKompanijeFragment extends DialogFragment {
    Button nazadBtn;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DetaljiKompanijeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetaljiKompanijeFragment newInstance() {
        DetaljiKompanijeFragment fragment = new DetaljiKompanijeFragment();
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


        nazadBtn =(Button) view.findViewById(R.id.nazadBtn);

        nazadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;
    }

}
