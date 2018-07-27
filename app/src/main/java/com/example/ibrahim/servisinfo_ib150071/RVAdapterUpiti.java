package com.example.ibrahim.servisinfo_ib150071;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ibrahim on 27.5.2018..
 */

public class RVAdapterUpiti extends RecyclerView.Adapter<RVAdapterUpiti.PersonViewHolder>
    {
        public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.NazivKompanijeTxt);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

        List<Person> persons;

        RVAdapterUpiti(List<Person> persons){
        this.persons = persons;
    }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

        @Override
        public RVAdapterUpiti.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemupiti, viewGroup, false);
            RVAdapterUpiti.PersonViewHolder pvh = new RVAdapterUpiti.PersonViewHolder(v);
        return pvh;
    }

        @Override
        public void onBindViewHolder(RVAdapterUpiti.PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personAge.setText(persons.get(i).age);
    }

        @Override
        public int getItemCount() {
        return persons.size();
    }
    }
