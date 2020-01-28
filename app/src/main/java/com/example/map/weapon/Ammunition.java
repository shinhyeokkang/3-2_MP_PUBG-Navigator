package com.example.map.weapon;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.Adapter.AmmunitionAdapter;
import com.example.map.Adapter.SliderAdapter;
import com.example.map.Detail;
import com.example.map.R;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class Ammunition extends AppCompatActivity {
    ArrayList<AmmunitionAdapter> ammunitionsList;

    private String[] imageUrls = {"https://i.imgur.com/IcGW4Y5.png", "https://i.imgur.com/80M9PHn.png", "https://i.imgur.com/Pgfbmyl.png", "https://i.imgur.com/kYwffmf.png"};
    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

    private CardSliderLayoutManager layoutManager;
    private RecyclerView recyclerView;


    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;
    private int currentPosition;

    private TextSwitcher descriptionSwitcher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ammunition_activity);

        ammunitionsList = new ArrayList<>();

        ammunitionsList.add(new AmmunitionAdapter("12 Gauge", "https://i.imgur.com/IcGW4Y5.png", "Ammo for S1897, S686, S12K"));
        ammunitionsList.add(new AmmunitionAdapter(".300 Magnum", "https://i.imgur.com/80M9PHn.png", "Ammo for AWM"));
        ammunitionsList.add(new AmmunitionAdapter(".45 ACP", "https://i.imgur.com/Pgfbmyl.png", "Ammo for P1911, Tommy Gun, Vector"));
        ammunitionsList.add(new AmmunitionAdapter("5.56mm", "https://i.imgur.com/kYwffmf.png", "Ammo for M16A4, M416, SCAR-L, M249, Mini14"));

        initRecyclerView();
        initWeaponTitle();
        initSwitchers();
    }

    private void initSwitchers() {
        AmmunitionAdapter ammoInPosition = ammunitionsList.get(0);

        descriptionSwitcher =findViewById(R.id.ammo_description);
        descriptionSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescription, true));

        descriptionSwitcher.setCurrentText(ammoInPosition.getAmmoDesc());
    }

    private class TextViewFactory implements ViewSwitcher.ViewFactory{

        final int styleid;
        final boolean center;

        TextViewFactory(int styleid, boolean center){
            this.styleid=styleid;
            this.center=center;
        }
        @Override
        public View makeView() {
            final TextView textView = new TextView(Ammunition.this);

            if(center){
                textView.setGravity(Gravity.CENTER);
            }
            textView.setTextAppearance(Ammunition.this, styleid);
            return textView;
        }

    }

    private void initWeaponTitle(){

        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView=(TextView)findViewById(R.id.ammo_title1);
        weapon2TextView=(TextView)findViewById(R.id.ammo_title2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(ammunitionsList.get(0).getAmmoName());
        weapon2TextView.setAlpha(0f);
    }
    private void setWeaponTitle(String text, boolean left2right) {

        final TextView invisibleText;
        final TextView visibleText;

        if (weapon1TextView.getAlpha() > weapon2TextView.getAlpha()) {
            visibleText = weapon1TextView;
            invisibleText = weapon2TextView;
        } else {
            visibleText = weapon2TextView;
            invisibleText = weapon1TextView;
        }


        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = weaponOffset2;
        } else {
            invisibleText.setX(weaponOffset2);
            vOffset = 0;
        }
        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", weaponOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.start();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManager = (CardSliderLayoutManager) recyclerView.getLayoutManager();

        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void onActiveCardChange() {
        final int pos = layoutManager.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        AmmunitionAdapter ammunitionInPosition = ammunitionsList.get(pos % ammunitionsList.size());

        final boolean left2right = pos < currentPosition;

        setWeaponTitle(ammunitionInPosition.getAmmoName(), left2right);

        descriptionSwitcher.setText(ammunitionInPosition.getAmmoName());

        currentPosition = pos;
    }
    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm = (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                final Intent intent = new Intent(Ammunition.this, Detail.class);
                intent.putExtra(Detail.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(Ammunition.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }

}
