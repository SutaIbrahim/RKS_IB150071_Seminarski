package com.example.ibrahim.servisinfo_ib150071.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ibrahim on 27.7.2018..
 */

public class GradoviResultVM implements Serializable {
    public static class Row implements Serializable
    {
        public int GradID ;
        public String Naziv ;
    }

    public List<GradoviResultVM.Row> rows;


}
