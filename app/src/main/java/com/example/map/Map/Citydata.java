package com.example.map.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map.R;

public class Citydata extends AppCompatActivity {

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent(); // get intent from Map
        setContentView(R.layout.activity_citydata);

        TextView number1 = (TextView)(findViewById(R.id.number1_textView)); //connect text view1
        TextView number2 = (TextView)(findViewById(R.id.number2_textView)); //connect text view2
        ImageView localpic = (ImageView)(findViewById(R.id.imageView2));

        String n1 = intent.getExtras().getString("localcode");
        number1.setText(n1);


        Toast.makeText(getBaseContext(),n1,Toast.LENGTH_SHORT).show();

        if(n1.equals("Rozhok")) {

            number2.setText(Explain.n2);
            //number2.setText(n2);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.rozhok);

            localpic.setImageBitmap(mBitmap);

        }//Rozhok
        if(n1.equals("Military base")) {

            number2.setText(Explain.n3);

            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.milba);
            localpic.setImageBitmap(mBitmap);

        }//Military base
        if(n1.equals("Yasnaya Polyana")) {
            number2.setText(Explain.n4);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.yas);

            localpic.setImageBitmap(mBitmap);

        }//Yasnaya Polyana
        if(n1.equals("North Georgopol")) {
            number2.setText(Explain.n5);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.gangbok);

            localpic.setImageBitmap(mBitmap);

        }//North Georgopol
        if(n1.equals("South Georgopol")) {
            number2.setText(Explain.n6);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.geo);

            localpic.setImageBitmap(mBitmap);

        }//South Georgopol
        if(n1.equals("Lipovka")) {
            number2.setText(Explain.n7);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lip);

            localpic.setImageBitmap(mBitmap);

        }//Lipovka
        if(n1.equals("Mylta")) {
            number2.setText(Explain.n8);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mylta);

            localpic.setImageBitmap(mBitmap);

        }//Mylta
        if(n1.equals("Novorepnoye")) {
            number2.setText(Explain.n9);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.novo);

            localpic.setImageBitmap(mBitmap);

        }//Novorepnoye
        if(n1.equals("Serverny")) {
            number2.setText(Explain.n10);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.serverny);

            localpic.setImageBitmap(mBitmap);

        }//Serverny
        if(n1.equals("Pochinki")) {
            number2.setText(Explain.n11);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pochinki);

            localpic.setImageBitmap(mBitmap);

        }//Pochinki
        if(n1.equals("Zharki")) {
            number2.setText(Explain.n12);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.zharki);

            localpic.setImageBitmap(mBitmap);

        }//Zharki
        if(n1.equals("Primorsk")) {
            number2.setText(Explain.n13);
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.primosk);

            localpic.setImageBitmap(mBitmap);

        }//Primorsk

    }
}

