package com.example.map.weapon;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.Adapter.AssaultsAdapter;
import com.example.map.Adapter.SliderAdapter;
import com.example.map.Detail;
import com.example.map.R;
import com.ramotion.cardslider.CardSliderLayoutManager;

import java.util.ArrayList;

public class Weapon extends AppCompatActivity {

    ArrayList<AssaultsAdapter> assaultsList;

    private final String[] imageUrls = {"https://i.imgur.com/0k7ZFWr.png", "https://i.imgur.com/zgfiWXw.png", "https://i.imgur.com/bShCyeV.png", "https://i.imgur.com/NJEJUeJ.png", "https://i.imgur.com/ZKo0HYi.png"};
    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;
    private TextSwitcher ammoSwitcher;

    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;

    private int currentPosition;
    private String weaponCategory;

    private TextSwitcher descriptionSwitcher;

    private ProgressBar hitProgress;
    private ProgressBar bulletProgress;
    private ProgressBar impactProgress;
    private ProgressBar rangeProgress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weapon_main);

        weaponCategory = getIntent().getExtras().getString("weaponType");
        assaultsList = new ArrayList<AssaultsAdapter>();

        assaultsList.add(new AssaultsAdapter("AKM","https://i.imgur.com/0k7ZFWr.png", "this is AKM", "7.62mm",715, 10000, 380, 49));
        assaultsList.add(new AssaultsAdapter("AUG","https://i.imgur.com/zgfiWXw.png", "this is Aug", "5.56mm",940, 9000, 350, 43));
        assaultsList.add(new AssaultsAdapter("GROZA","https://i.imgur.com/bShCyeV.png", "this is Groza", "7.62mm",715, 10000, 380, 49));
        assaultsList.add(new AssaultsAdapter("M16A4","https://i.imgur.com/NJEJUeJ.png", "this is M16", "5.56mm",900, 8000, 360, 43));
        assaultsList.add(new AssaultsAdapter("M416","https://i.imgur.com/ZKo0HYi.png", "this is M4", "5.56mm",880, 3500, 400, 43));

        initRecyclerView();
        initWeaponTitle();
        initSwitcher();

    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    onActiveCardChange();
                }
            }
        });
        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();

    }

    protected void onPause() {
        super.onPause();
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);

    }

    private void onActiveCardChange(int pos){
        AssaultsAdapter assaultInPosition = assaultsList.get(pos % assaultsList.size());
        final boolean left2right = pos < currentPosition;

        ammoSwitcher.setText(assaultInPosition.getAmmo());
        setWeaponTitle(assaultInPosition.getWeaponName(), left2right);
        descriptionSwitcher.setText(assaultInPosition.getWeaponDesc());

        hitProgress.setProgress(assaultInPosition.getHitDamage());
        bulletProgress.setProgress(assaultInPosition.getBulletSpeed());
        impactProgress.setProgress(assaultInPosition.getImpactPower());
        rangeProgress.setProgress(assaultInPosition.getRange());

        currentPosition=pos;
    }

    private void initSwitcher(){
        AssaultsAdapter currentAssault = assaultsList.get(0);
        ammoSwitcher=findViewById(R.id.ammo);
        ammoSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescription,true));
        ammoSwitcher.setCurrentText(currentAssault.getAmmo());

        descriptionSwitcher = findViewById(R.id.weapon_description);
        descriptionSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescription,true));
        descriptionSwitcher.setCurrentText(currentAssault.getWeaponDesc());

        hitProgress = findViewById(R.id.damageprogress);
        hitProgress.setProgress(currentAssault.hitDamage);

        bulletProgress = findViewById(R.id.bulletprogress);
        bulletProgress.setProgress(currentAssault.bulletSpeed);

        impactProgress = findViewById(R.id.impactprogress);
        impactProgress.setProgress(currentAssault.impactPower);

        rangeProgress = findViewById(R.id.rangeprogress);
        rangeProgress.setProgress(currentAssault.range);
    }

    private void initWeaponTitle(){
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView)findViewById(R.id.weapon1);
        weapon2TextView = (TextView)findViewById(R.id.weapon2);
        weapon1TextView.setText(assaultsList.get(0).getWeaponName());
        weapon2TextView.setAlpha(0f);
    }

    private void setWeaponTitle(String text, boolean left2right){
        final TextView invisibleText;
        final TextView visibleText;

        if(weapon1TextView.getAlpha() > weapon2TextView.getAlpha()){
            visibleText =weapon1TextView;
            invisibleText = weapon2TextView;
        } else {
            visibleText = weapon2TextView;
            invisibleText =weapon1TextView;
        }

        final int vOffset;
        if(left2right){
            invisibleText.setX(0);
            vOffset=weaponOffset2;
        } else {
            invisibleText.setX(weaponOffset2);
            vOffset=0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText,"alpha",1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText,"alpha", 0f);
        final ObjectAnimator ix = ObjectAnimator.ofFloat(invisibleText,"x",weaponOffset1);
        final ObjectAnimator vx = ObjectAnimator.ofFloat(visibleText,"x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha,vAlpha,ix,vx);
        animSet.start();
    }

    private class TextViewFactory implements ViewSwitcher.ViewFactory {

        final int styleid;
        final boolean center;
        TextViewFactory(int styleid, boolean center){
            this.styleid=styleid;
            this.center=center;
        }
        @Override
        public View makeView() {
            final TextView textView = new TextView (Weapon.this);

            if(center){
                textView.setGravity(Gravity.CENTER);
            }
            textView.setTextAppearance(styleid);

            return textView;
        }
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
                final Intent intent = new Intent(Weapon.this, Detail.class);
                intent.putExtra(Detail.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(Weapon.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }



}
