package com.example.ibrahim.servisinfo_ib150071;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrahim.servisinfo_ib150071.Helper.MyApiRequest;
import com.example.ibrahim.servisinfo_ib150071.Helper.MyRunnable;
import com.example.ibrahim.servisinfo_ib150071.data.Global;
import com.example.ibrahim.servisinfo_ib150071.data.UpitPostVM;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class DodajUpitActivity extends AppCompatActivity {

    EditText naslovTxt;
    EditText modelTxt;
    EditText opisTxt;
    Button posaljiUpitBtn;
    Button dodajSlikuBtn;
    Bitmap slika;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_upit);

        slika=null;

        naslovTxt = (EditText) findViewById(R.id.NaslovTxt);
        modelTxt = (EditText) findViewById(R.id.ModelTxt);
        opisTxt = (EditText) findViewById(R.id.opisTxt);
        dodajSlikuBtn =(Button) findViewById(R.id.DodajSlikuBtn);
        posaljiUpitBtn=(Button) findViewById(R.id.PosaljiUpitBtn);

        dodajSlikuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                dodajSliku_click();

            }
        });


        posaljiUpitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                posaljiBtn_click();

            }
        });






    } //end onCreate



    private void dodajSliku_click() {
        if (dodajSlikuBtn.getText() == "Izbrisi sliku (1)") {

            slika = null;
            dodajSlikuBtn.setText("Dodaj sliku");
            dodajSlikuBtn.setTextColor(Color.WHITE);
            Toast.makeText(DodajUpitActivity.this, "Slika uspjesno izbrisana", Toast.LENGTH_LONG).show();

        } else {
            izaberiSliku();
        }
    }

    private void izaberiSliku() {
        final int PICK_IMAGE = 1;

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }


    private void posaljiBtn_click() {
            String naslov=naslovTxt.getText().toString();
            String marka=modelTxt.getText().toString();
            String opis=opisTxt.getText().toString();
            //slika vec postavljena u onActivityResult

        UpitPostVM model=new UpitPostVM();

        model.MarkaUredjaja=marka;
        model.OpisKvara=opis;
        model.Naslov=naslov;
        model.KlijentID= Global.prijavljeniKlijent.KlijentID;
        model.ZeljeniDatumPrijemaOd=null;
        model.ZeljeniDatumPrijemaDo=null;
        model.Datum= Calendar.getInstance().getTime();
        model.Odgovoreno=false;
        model.KompanijaID= Integer.parseInt(Global.izabranaKompanijaID);

        //slika bitmap to byte[]
        if(slika != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            slika.compress(Bitmap.CompressFormat.JPEG, 20, stream);
            byte[] byteArray = stream.toByteArray();

            // Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length); - obrnuto

            // na apiju se opet prevodi u byte[]
            String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);


            model.EncodedImage = encodedImage;
        }
        else {
            model.EncodedImage="0";
        }

        MyApiRequest.post(this, "api/Upiti", model, new MyRunnable<UpitPostVM>() {
            @Override
            public void run(UpitPostVM x) {
                Toast.makeText(DodajUpitActivity.this,"Upit uspjesno poslan",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }



    // za sliku
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                slika=selectedImage;

               // image_view.setImageBitmap(selectedImage); //pregled izabrane slike

                dodajSlikuBtn.setText("Izbrisi sliku (1)");
                dodajSlikuBtn.setTextColor(Color.rgb(255,127,80));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(DodajUpitActivity.this, "Doslo je do greske", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(DodajUpitActivity.this, "Slika nije izabrana",Toast.LENGTH_LONG).show();
        }
    }




}
