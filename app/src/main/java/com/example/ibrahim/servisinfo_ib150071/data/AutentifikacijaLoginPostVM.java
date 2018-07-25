package com.example.ibrahim.servisinfo_ib150071.data;

/**
 * Created by Ibrahim on 25.7.2018..
 */

import java.io.Serializable;

public class AutentifikacijaLoginPostVM implements Serializable
{
    public String username ;
    public String password ;

    public AutentifikacijaLoginPostVM(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}