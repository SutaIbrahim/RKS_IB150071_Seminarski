package com.example.ibrahim.servisinfo_ib150071.Helper;

/**
 * Created by Ibrahim on 25.7.2018..
 */


public class MyApiResult
{
    public String errorMessage = "";
    public boolean isException = false;
    public int resultCode = 0;
    public String value;


    private MyApiResult()
    {

    }

    public static MyApiResult Error(int exceptionCode, String exceptionMessage)
    {
        MyApiResult x = new MyApiResult();
        x.isException = true;
        x.resultCode = exceptionCode;
        x.errorMessage = exceptionMessage;

        return x;
    }
    public static MyApiResult OK(String value)
    {
        MyApiResult x = new MyApiResult();
        x.value = value;
        x.isException = false;
        x.resultCode = 200;
        x.errorMessage = "";

        return x;
    }
}