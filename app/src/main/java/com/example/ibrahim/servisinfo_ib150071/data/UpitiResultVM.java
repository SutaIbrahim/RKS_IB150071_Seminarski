package com.example.ibrahim.servisinfo_ib150071.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ibrahim on 28.7.2018..
 */

public class UpitiResultVM implements Serializable{

    public static class Row implements Serializable {
        public int UpitID;
        public String OpisKvara;
        public String MarkaUredjaja; // atribut marka je i za model i marku
        public String Naslov;
        public byte[] Slika;
        public String KlijentID;
    }
    public List<UpitiResultVM.Row> rows;

}
