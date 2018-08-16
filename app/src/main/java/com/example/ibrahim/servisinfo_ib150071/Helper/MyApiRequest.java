package com.example.ibrahim.servisinfo_ib150071.Helper;

/**
 * Created by Ibrahim on 25.7.2018..
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.ibrahim.servisinfo_ib150071.LoginActivity;

import java.lang.reflect.Type;

public class MyApiRequest {
    private static <T> void request(final Activity activity, final String urlAction, final MyUrlConnection.HttpMethod httpMethod, final Object postObject, final MyRunnable<T> myCallback) {
        new AsyncTask<Void, Void, MyApiResult>() {
           private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(activity, "Učitavanje", "Sačekajte...");
            }

            @Override
            protected MyApiResult doInBackground(Void... voids)
            {
                String jsonPostObject = postObject==null?null:MyGson.build().toJson(postObject);
                return MyUrlConnection.request(MyConfig.baseUrl +"/"+ urlAction, httpMethod, jsonPostObject, "application/json");
            }

            @Override
            protected void onPostExecute(MyApiResult result) {

               progressDialog.dismiss();

                if (result.isException)
                {
                    if (result.resultCode == 401)
                    {
                        Toast.makeText(activity, "Niste prijavljeni", Toast.LENGTH_LONG).show();
                        activity.startActivity(new Intent(activity, LoginActivity.class));
                    }
                    else {
                        View parentLayout = activity.findViewById(android.R.id.content);
                        Snackbar snackbar;
                        if (result.resultCode == 0) {
                            snackbar = Snackbar.make(parentLayout, "Greška u komunikaciji sa serverom, provjerite internet konekciju ili pokusajte kasnije", Snackbar.LENGTH_LONG);
                                snackbar.setAction("Ponovi", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyApiRequest.get(activity, urlAction, myCallback);
                            }
                        });
                        snackbar.show();
                        } else {

                           /* snackbar = Snackbar.make(parentLayout, "Došlo je do greške, pokušajte opet",Snackbar.LENGTH_LONG);*/

                            /*snackbar = Snackbar.make(parentLayout, "Greška " + result.resultCode + ": " + result.errorMessage, Snackbar.LENGTH_LONG);*/
                            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                            alertDialog.setTitle("Greška");
                            alertDialog.setMessage("Doslo je do greške, provjerite internet konekciju ili pokusajte kasnije");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pokušaj opet",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            MyApiRequest.get(activity, urlAction, myCallback);
                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Nazad",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();

                        }

                    /*    snackbar.setAction("Ponovi", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyApiRequest.get(activity, urlAction, myCallback);
                            }
                        });
                        snackbar.show();*/
                    }
                }
                else
                {
                    if (myCallback !=null) {
                        Type genericType = myCallback.getGenericType();

                        T x = null;
                        try {
                           x = MyGson.build().fromJson(result.value, genericType);



                        } catch (Exception e) {

                          /*  try {
                               *//* Type collectionType = new TypeToken<Collection<T>>() {  // za listu
                                }.getType();
                                Collection<T> enums = MyGson.build().fromJson(result.value, collectionType);
                                myCallback.run(enums);*//*


                            } catch (Exception ee) {*/
                                View parentLayout = activity.findViewById(android.R.id.content);
                                Snackbar.make(parentLayout, "Doslo je do greske unutar aplikacije!", Snackbar.LENGTH_LONG).show();
                            }
                        //}
                        myCallback.run(x);
                    }
                }
            }
        }.execute();
    }


    public static <T> void get(final Activity activity, final String urlAction, final MyRunnable<T> myCallback)
    {
        request(activity, urlAction, MyUrlConnection.HttpMethod.GET, null, myCallback);
    }

    public static <T> void delete(Activity activity, String urlAction, MyRunnable<T> myCallback)
    {
        request(activity, urlAction, MyUrlConnection.HttpMethod.DELETE, null, myCallback);
    }

    public static <T> void post(Activity activity, final String urlAction, Object postObject,  final MyRunnable<T> myCallback)
    {
        request(activity, urlAction, MyUrlConnection.HttpMethod.POST, postObject, myCallback);
    }

    public static <T> void put(Activity activity, final String urlAction, Object postObject,  final MyRunnable<T> myCallback)
    {
        request(activity, urlAction, MyUrlConnection.HttpMethod.PUT, postObject, myCallback);
    }
}
