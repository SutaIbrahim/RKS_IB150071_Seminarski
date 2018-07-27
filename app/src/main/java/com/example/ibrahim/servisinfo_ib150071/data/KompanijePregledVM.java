package com.example.ibrahim.servisinfo_ib150071.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ibrahim on 25.7.2018..
 */

public class KompanijePregledVM implements Serializable {
    public static class Row implements Serializable
    {
        public int KompanijaID ;
        public String Naziv ;
        public String Adresa;
        public String Telefon ;
        public String Email ;
        public String KorisickoIme;
        public String LozinkaSalt ;
        public String LozinkaHash ;
        public int GradID ;
        public String Grad ;

    }

    public List<Row> rows;
}