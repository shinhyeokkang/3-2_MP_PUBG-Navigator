package com.example.map.Map;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.map.R;


public class PinView extends SubsamplingScaleImageView {

    private final Paint paint = new Paint();
    private final PointF vPin = new PointF();
    private final PointF vPin2 = new PointF();
    private PointF sPin;
    private PointF sPin2;
    private Bitmap pin;
    static float vX;
    static float vY;

    private int strokeWidth = 4;
    private final PointF vCenter = new PointF();
    private final PointF v1 = new PointF();
    private final PointF v2 = new PointF();
    private final PointF v3 = new PointF();

/////////////////비행기 변수

    private int strokeWidth2;
    private static final float FIRST_DOT_RADIUS = 5;   // 첫 터치 지점에 그려지는 점의 반지름 크기
    private static final float AIRPLANE_PATH_LINE_WIDTH = 4;                // 비행기 경로 라인 두께

    private static final int AIRPLANE_PATH_LINE_AREA_COLOR_FIRST  = 0x55FF0000;   // 비행기 경로 영역 - 1지역
    private static final int AIRPLANE_PATH_LINE_AREA_COLOR_SECOND = 0x55FFBF24;   // 비행기 경로 영역 - 2지역


    private float firstX;
    private float firstY;
    private float secondX;
    private float secondY;

    private int mode;
///////////////////////////


    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        initialise();
    }

    private double getDistance(double x1, double y1, double x2, double y2 ){

        return Math.sqrt(Math.pow(Math.abs(x2-x1),2)+Math.pow(Math.abs(y2-y1),2));

    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialise();
        invalidate();
    }
    public void setPin2(PointF sPin2) {
        this.sPin2 = sPin2;
        initialise();
        invalidate();
    }

    public void finishPin(){
        this.sPin = sPin;
        invalidate();
    }

    private void initialise() {
        float density = getResources().getDisplayMetrics().densityDpi;
        pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
        float w = (density/800f) * pin.getWidth();
        float h = (density/800f) * pin.getHeight();

        pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }
        float radius = (getScale() * getSWidth()) * 0.03f;
        float radius_big = (getScale() * getSWidth()) * 0.05f;




        // 지역별 터치가능영역 원그리기
        //Rozhok
        sourceToViewCoord(1920,1380, vCenter);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(vCenter.x, vCenter.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(vCenter.x, vCenter.y, radius, paint);


        //Zharki
        sourceToViewCoord(539,595, v1);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v1.x, v1.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v1.x, v1.y, radius, paint);

        //Serverny
        sourceToViewCoord(1813,595, v2);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v2.x, v2.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v2.x, v2.y, radius, paint);

        //Yasnaya Polyana
        sourceToViewCoord(2600,1153, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);

        //North Georgopol
        sourceToViewCoord(866,1048, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);

        //South Georgopol
        sourceToViewCoord(736,1379, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Lipovka
        sourceToViewCoord(3361,1572, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Mylta
        sourceToViewCoord(2858,2277, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Novorepnoye
        sourceToViewCoord(2912,2878, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Pochinki
        sourceToViewCoord(1733,1933, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Primorsk
        sourceToViewCoord(777,2904, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius, paint);

        //Military base
        sourceToViewCoord(2125,3035, v3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.argb(80, 200, 120, 50));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(v3.x, v3.y, radius_big, paint);



        //터치 지역에 따라 핀찍기
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(255, 200, 120, 50));

        if (sPin != null && pin != null) {
            sourceToViewCoord(sPin, vPin);
            vX = vPin.x - (pin.getWidth()/2);
            vY = vPin.y - pin.getHeight();
            canvas.drawBitmap(pin, vX, vY, paint);
        }
        if (sPin2 != null && pin != null) {
            sourceToViewCoord(sPin2, vPin2);
            float vX2 = vPin2.x - (pin.getWidth()/2);
            float vY2 = vPin2.y - pin.getHeight();
            canvas.drawBitmap(pin, vX2, vY2, paint);
            paint.setStrokeWidth(5f);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GRAY);
            if(sPin2.x !=-100) {
                canvas.drawLine(vPin.x, vPin.y, vPin2.x, vPin2.y, paint);
            }

        }

        float scale = getScale()*600;

        String ss = String.valueOf(vPin.x);
        //Toast.makeText(getContext(),dis,Toast.LENGTH_SHORT).show();

        switch(mode){
            case 1 :
                sourceToViewCoord(firstX, firstY, v3);
                paint.setColor(Color.RED);
                canvas.drawCircle(v3.x, v3.y, FIRST_DOT_RADIUS, paint);
                break;
            case 2 :
                ////    비행기의 경로 주변 경계 지역 칠하기 - 2지역    ////
                sourceToViewCoord(firstX, firstY, v3);
                sourceToViewCoord(secondX, secondY, v2);
                float XX = 100000;
                float XX2 = -100000;
                float grad = (v3.y-v2.y)/(v3.x-v2.x);
                float rest = v2.y - grad*v2.x;
                float dis = (float) getDistance(v3.x, v3.y, v2.x, v2.y);
                String dd = String.valueOf(scale);
                String rr = String.valueOf(rest);
                //Toast.makeText(getContext(),dd,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Red: Danger Zone  Green: Maximum reach",Toast.LENGTH_SHORT).show();
                paint.setColor(AIRPLANE_PATH_LINE_AREA_COLOR_SECOND);
                paint.setStrokeWidth(scale*2);
                canvas.drawLine(XX,grad*XX+rest, XX2,grad*XX2+rest, paint);   // 경계 지역 그리기

                ////    비행기의 경로 주변 경계 지역 칠하기 - 1지역    ////
                paint.setColor(AIRPLANE_PATH_LINE_AREA_COLOR_FIRST);

                paint.setStrokeWidth(scale);
                canvas.drawLine(XX,grad*XX+rest, XX2,grad*XX2+rest, paint);   // 경계 지역 그리기

                ////    비행기의 경로 그리기   ////
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(AIRPLANE_PATH_LINE_WIDTH);
                canvas.drawLine(v3.x, v3.y, v2.x, v2.y, paint);   // 선 그리기

                ////    첫 포인트 점으로 그리기   ////
                paint.setColor(Color.RED);
                canvas.drawCircle(v3.x, v3.y, FIRST_DOT_RADIUS, paint);

                ////    두번째 포인트 점으로 그리기   ////
                paint.setColor(Color.BLUE);
                canvas.drawCircle(v2.x, v2.y, FIRST_DOT_RADIUS, paint);

                break;
        }
    }

    public void drawFirstCircle(float x, float y){
        firstX = x;
        firstY = y;
        mode = 1;
        invalidate();
    }

    public void drawSecondCircle(float x, float y){
        secondX = x;
        secondY = y;
        mode = 2;
        invalidate();
    }

}