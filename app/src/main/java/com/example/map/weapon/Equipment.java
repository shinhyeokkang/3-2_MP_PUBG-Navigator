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
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.Adapter.SliderAdapter;
import com.example.map.Adapter.EquipmentAdapter;
import com.example.map.Detail;
import com.example.map.R;
import com.ramotion.cardslider.CardSliderLayoutManager;

import java.util.ArrayList;

public class Equipment extends AppCompatActivity {
    ArrayList<EquipmentAdapter> EquipmentList;
    private final String[] imageUrls = {"https://i.imgur.com/gyHbJRp.png", "https://i.imgur.com/34ifn4M.png", "https://i.imgur.com/WU0RdHW.png", "https://i.imgur.com/cctbksj.png"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());
    private CardSliderLayoutManager layoutManger;
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
        setContentView(R.layout.cardslide_weapon_activity);

        EquipmentList = new ArrayList<>();

        EquipmentList.add(new EquipmentAdapter("Motorcycle Helmet 1", "https://i.imgur.com/gyHbJRp.png", "level 1 helmet so weak"));
        EquipmentList.add(new EquipmentAdapter("Helmet 2", "https://i.imgur.com/34ifn4M.png", "level 2 helmet so so"));
        EquipmentList.add(new EquipmentAdapter("Helmet 3", "https://i.imgur.com/WU0RdHW.png", "level 3 helmet so hard"));
        EquipmentList.add(new EquipmentAdapter("Vest 1", "https://i.imgur.com/cctbksj.png", "police vest"));

        initRecyclerView();
        initWeaponTitle();
        initSwitchers();
    }
    private void initSwitchers() {
        EquipmentAdapter currentThrowable = EquipmentList.get(0);
        descriptionSwitcher = findViewById(R.id.description);
        descriptionSwitcher.setFactory(new Equipment.TextViewFactory(R.style.WeaponDescription, true));
        descriptionSwitcher.setText(currentThrowable.getEquipmentDesc());


    }

    private void initWeaponTitle() {

        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.title1);
        weapon2TextView = (TextView) findViewById(R.id.title2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(EquipmentList.get(0).getEquipmentname());
        weapon2TextView.setAlpha(0f);
    }
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
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

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();


    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        EquipmentAdapter equipmentInposition = EquipmentList.get(pos % EquipmentList.size());
        final boolean left2right = pos < currentPosition;
        setWeaponTitle(equipmentInposition.getEquipmentname(), left2right);

        descriptionSwitcher.setText(equipmentInposition.getEquipmentDesc());

        currentPosition = pos;

    }

    private void setWeaponTitle(String equipmentname, boolean left2right) {
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

        invisibleText.setText(equipmentname);
        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", weaponOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.start();
    }
    private class TextViewFactory implements ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(Equipment.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }
            textView.setTextAppearance(Equipment.this, styleId);

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
                final Intent intent = new Intent(Equipment.this, Detail.class);
                intent.putExtra(Detail.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(Equipment.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }


}