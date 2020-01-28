package com.example.map.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.map.R;


public class Map extends AppCompatActivity {

    private int mTouchCount = 0;
    private float mTouchPointFirstX = 0;
    private float mTouchPointFirstY = 0;
    private float mTouchPointSecondX = 0;
    private float mTouchPointSecondY = 0;
    private PinView mImageViewFullMap;
    private SubsamplingScaleImageView mCargunview;
    //private ImageViewFullMap mImageViewFullMapForDraw;



    boolean a= false;
    boolean b= false;
    boolean c= false;
    boolean d= false;
    private float[] value = new float[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        mImageViewFullMap =
                (PinView) findViewById(R.id.imageViewFullMap);

        mCargunview =
                (SubsamplingScaleImageView) findViewById(R.id.cargunview);

        mCargunview.setVisibility(View.GONE);
        mCargunview.setClickable(false);



        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (mImageViewFullMap.isReady()){
                    PointF sCoord = mImageViewFullMap.viewToSourceCoord(e.getX(),e.getY());
                    TextView tv = (TextView) findViewById(R.id.textView);

                    String coordinate = "x,y is"+sCoord;
                    tv.setText(coordinate);

                    if(sCoord.x<=2000&&sCoord.x>=1850){
                        if(sCoord.y<=1450&&sCoord.y>=1300) {
                            Toast.makeText(Map.this, "This is Rozhok!!", Toast.LENGTH_SHORT).show();

                            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject"); //이런식으로 intent 와 extra 로 각 지역별 코드 넘겨줌
                            //받는 activity 에서는 각 지역 코드별로 if 문 만들어서 해당 지역이름 textview, 지역파밍루트 imageview, 지역설명 textview 출력

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Rozhok"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Rozhok
                    if(sCoord.x<=2400&&sCoord.x>=1850){
                        if(sCoord.y<=3180&&sCoord.y>=2921) {
                            Toast.makeText(Map.this, "This is Military base!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Military base"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Military base
                    if(sCoord.x<=2727&&sCoord.x>=2440){
                        if(sCoord.y<=1235&&sCoord.y>=1050) {
                            Toast.makeText(Map.this, "This is Yasnaya Polyana!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Yasnaya Polyana"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Yasnaya Polyana
                    if(sCoord.x<=1100&&sCoord.x>=613){
                        if(sCoord.y<=1192&&sCoord.y>=942) {
                            Toast.makeText(Map.this, "This is North Georgopol!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "North Georgopol"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//North Georgopol
                    if(sCoord.x<=1046&&sCoord.x>=557){
                        if(sCoord.y<=1416&&sCoord.y>=1223) {
                            Toast.makeText(Map.this, "This is South Georgopol!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "South Georgopol"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//South Georgopol
                    if(sCoord.x<=3432&&sCoord.x>=3272){
                        if(sCoord.y<=1683&&sCoord.y>=1509) {
                            Toast.makeText(Map.this, "This is Lipovka!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Lipovka"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Lipovka
                    if(sCoord.x<=2933&&sCoord.x>=2748){
                        if(sCoord.y<=2372&&sCoord.y>=2218) {
                            Toast.makeText(Map.this, "This is Mylta!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Mylta"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Mylta
                    if(sCoord.x<=3048&&sCoord.x>=2807){
                        if(sCoord.y<=2974&&sCoord.y>=2801) {
                            Toast.makeText(Map.this, "This is Novorepnoye!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Novorepnoye"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Novorepnoye
                    if(sCoord.x<=1894&&sCoord.x>=1728){
                        if(sCoord.y<=678&&sCoord.y>=546) {
                            Toast.makeText(Map.this, "This is Serverny!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Serverny"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Serverny
                    if(sCoord.x<=1826&&sCoord.x>=1627){
                        if(sCoord.y<=2022&&sCoord.y>=1872) {
                            Toast.makeText(Map.this, "This is Pochinki!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Pochinki"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Pochinki
                    if(sCoord.x<=645&&sCoord.x>=440){
                        if(sCoord.y<=675&&sCoord.y>=546) {
                            Toast.makeText(Map.this, "This is Zharki!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Zharki"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Zharki
                    if(sCoord.x<=864&&sCoord.x>=620){
                        if(sCoord.y<=3044&&sCoord.y>=2787) {
                            Toast.makeText(Map.this, "This is Primorsk!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplication(),Citydata.class);
                            intent.putExtra("localcode", "Primorsk"); //get the number1 to intent

                            startActivity(intent); // start new intent
                        }
                    }//Primorsk



                }
                return true;
            }


        });

        mImageViewFullMap.setImage(ImageSource.resource(R.drawable.erangel)); //화면에 에란겔 지도 띄워주기

        mImageViewFullMap.isZoomEnabled();
        mImageViewFullMap.getCenter();

        mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return gestureDetector.onTouchEvent(motionEvent);

            }


        });



        RelativeLayout Re = (RelativeLayout) findViewById(R.id.re);
        Re.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        float absolute_x = event.getX();
                        float absolute_y = event.getY();
                        TextView tv = (TextView) findViewById(R.id.textView);

                        String coordinate = "x is "+ absolute_x + "y is" +absolute_y;
                        tv.setText(coordinate);
                        Toast.makeText(Map.this, coordinate, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //final Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.erangel);

        PointF center = mImageViewFullMap.getCenter();
        float scale = mImageViewFullMap.getScale();
        String stre= center+" "+scale;
        Toast.makeText(this, "Map is erangel", Toast.LENGTH_SHORT).show();

        final Button route = (Button)findViewById(R.id.route);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a == false){
                    mImageViewFullMap.resetScaleAndCenter();
                    //mImageViewFullMap.setZoomEnabled(false);

                    //mImageViewFullMapForDraw = (ImageViewFullMap) findViewById(R.id.imageViewFullMapForDraw);
                    initTouch();
                    a=true;
                    route.setText("map");
                }
                else {
                    //mImageViewFullMap.setZoomEnabled(true);
                    mImageViewFullMap.setImage(ImageSource.resource(R.drawable.erangel));
                    mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return gestureDetector.onTouchEvent(motionEvent);
                        }
                    });
                    a=false;
                    route.setText("route");

                }
            }
        });
/*
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mImageViewFullMapForDraw.drawFirstCircle(-100,-100);
                mTouchCount=0;
            }
        });
        */
////////////////////////////////////////
        final Button distance = (Button)findViewById(R.id.distance);
        distance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mImageViewFullMap =
                        (PinView) findViewById(R.id.imageViewFullMap);

                if(b == false){
                    TextView tv = (TextView) findViewById(R.id.textView);
                    initDis();
                    b=true;
                    distance.setText("map");
                }
                else {
                    mImageViewFullMap.setImage(ImageSource.resource(R.drawable.erangel));
                    mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return gestureDetector.onTouchEvent(motionEvent);
                        }
                    });

                    mImageViewFullMap.setPin(new PointF((int)-100,(int)-100));
                    mImageViewFullMap.setPin2(new PointF((int)-100,(int)-100));
                    b=false;
                    distance.setText("Distance");

                }
            }
        });

        final Button cargun = (Button)findViewById(R.id.cargun);
        cargun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                if(c == false){
                    mCargunview.setVisibility(View.VISIBLE);
                    mCargunview.setClickable(true);
                    mCargunview.setImage(ImageSource.resource(R.drawable.car));
                    c=true;
                    cargun.setText("map");
                }
                else {
                    mCargunview.setVisibility(View.GONE);
                    mCargunview.setClickable(false);
                    mImageViewFullMap.setImage(ImageSource.resource(R.drawable.erangel));
                    a=false;
                    route.setText("route");
                    b=false;
                    distance.setText("Distance");


                    mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return gestureDetector.onTouchEvent(motionEvent);
                        }
                    });

                    c=false;
                    cargun.setText("Car&Gun");

                }
            }
        });
        final Button ship = (Button)findViewById(R.id.ship);
        ship.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                if(d == false){
                    mCargunview.setVisibility(View.VISIBLE);
                    mCargunview.setClickable(true);
                    mCargunview.setImage(ImageSource.resource(R.drawable.ship));
                    d=true;
                    ship.setText("map");
                }
                else {
                    mCargunview.setVisibility(View.GONE);
                    mCargunview.setClickable(false);
                    mImageViewFullMap.setImage(ImageSource.resource(R.drawable.erangel));
                    a=false;
                    route.setText("route");
                    b=false;
                    distance.setText("Distance");


                    mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return gestureDetector.onTouchEvent(motionEvent);
                        }
                    });

                    d=false;
                    ship.setText("Ship");

                }
            }
        });

    }
    ////////////////////////////////////
    private void initTouch(){
        mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        PointF sCoord = mImageViewFullMap.viewToSourceCoord(motionEvent.getX(),motionEvent.getY());
                        float x = sCoord.x;
                        float y = sCoord.y;
                        Log.v("erangel","==== x : "+x);
                        Log.v("erangel","==== y : "+y);
                        mTouchCount++;
                        if(mTouchCount == 1){
                            mTouchPointFirstX = x;
                            mTouchPointFirstY = y;
                            mImageViewFullMap.drawFirstCircle(mTouchPointFirstX, mTouchPointFirstY);
                        } else if(mTouchCount ==2){
                            mTouchPointSecondX = x;
                            mTouchPointSecondY = y;
                            mImageViewFullMap.drawSecondCircle(mTouchPointSecondX, mTouchPointSecondY);

                            // 터치 수 초기화

                        } else {
                            mTouchCount =0;
                            mImageViewFullMap.drawFirstCircle(-100,-100);
                        }

                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:

                }
                return false;

            }
        });
    }

    private void initDis(){
        mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        PointF sCoord = mImageViewFullMap.viewToSourceCoord(motionEvent.getX(),motionEvent.getY());

                        float x = sCoord.x;
                        float y = sCoord.y;
                        Log.v("erangel","==== x : "+x);
                        Log.v("erangel","==== y : "+y);
                        mTouchCount++;
                        if(mTouchCount == 1){

                            mImageViewFullMap.setPin(new PointF((int)sCoord.x,(int)sCoord.y));

                            mTouchPointFirstX = x;
                            mTouchPointFirstY = y;

                            TextView tv = (TextView) findViewById(R.id.textView);

                            String coordinate = "x,y is"+x+","+y;
                            tv.setText(coordinate);


                        } else if(mTouchCount ==2){

                            mImageViewFullMap.setPin2(new PointF((int)sCoord.x,(int)sCoord.y));

                            mTouchPointSecondX = x;
                            mTouchPointSecondY = y;

                            TextView tv = (TextView) findViewById(R.id.textView);
                            TextView disview = (TextView) findViewById(R.id.distext);


                            String coordinate = "x,y is"+x+","+y;
                            tv.setText(coordinate);

                            double dis = getDistance(mTouchPointFirstX,mTouchPointFirstY,mTouchPointSecondX,mTouchPointSecondY);
                            int meter = (int)dis*1000/490;

                            String met = String.valueOf(meter);

                            double time = dis*(1000/490)/6.25;
                            int showtime = (int)time;

                            if(showtime<60) {
                                String st = String.valueOf(showtime);

                                String mess = "Distance: " + met + "m" + "\r\n" + "Travel time: " + st + " seconds";
                                disview.setText(mess);
                            }
                            else{
                                int minut = showtime/60;
                                int sec = showtime%60;
                                String mins = String.valueOf(minut);
                                String secs = String.valueOf(sec);
                                String minmess = "Distance: "+ met + "m" + "\r\n"+"Travel time: "+mins+" minute "+ secs+" seconds";
                                disview.setText(minmess);
                            }

                            //Toast.makeText(Map.this,"It took "+ showtime + " seconds to get there!",Toast.LENGTH_SHORT).show();
                            // 터치 수 초기화

                        } else {
                            mTouchCount =0;
                            mImageViewFullMap.setPin(new PointF((int)-100,(int)-100));
                            mImageViewFullMap.setPin2(new PointF((int)-100,(int)-100));

                        }


                    case MotionEvent.ACTION_MOVE:

                    case MotionEvent.ACTION_UP:


                }

                return false;

            }
        });
    }
    private double getDistance(double x1, double y1, double x2, double y2 ){

        return Math.sqrt(Math.pow(Math.abs(x2-x1),2)+Math.pow(Math.abs(y2-y1),2));

    }


}


