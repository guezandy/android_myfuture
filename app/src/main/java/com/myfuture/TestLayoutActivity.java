package com.myfuture;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.myfuture.anim.HideAnimation;
import com.myfuture.anim.ShowAnimation;


public class TestLayoutActivity extends FragmentActivity {
    private ViewFlipper mCreatorView;
    private LinearLayout mBottomSlider;
    private LinearLayout mOutfitSelectSlider;
    private GridView mOutfitGrid;
    private View mButtonNewOutfit;
    private View mSelectorChevron;
    private boolean mOutfitCreatorOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_drawer);
        mOutfitSelectSlider = (LinearLayout) mCreatorView.findViewById(R.id.outfit_select_slider);
        mOutfitGrid = (GridView) mCreatorView.findViewById(R.id.grid_outfit);

        mBottomSlider = (LinearLayout) this.findViewById(R.id.bottom_slider);
        //hook up animation for bottom slider.
        mBottomSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOutfitCreatorOpen) {
                    closeBottomSlider();
                } else {
                    openBottomSlider();
                }
            }
        });
    }

    private void closeBottomSlider() {
        Animation anim = new HideAnimation(mOutfitSelectSlider, (int) getResources().getDimension(R.dimen.outfit_selector_height));
        mOutfitCreatorOpen = false;
        mOutfitSelectSlider.startAnimation(anim);
        mSelectorChevron.setRotation(180);
    }

    /**
     * Encapsulates code to open bottom slider, since this will likely be called from a couple of different places.
     */
    private void openBottomSlider() {
        Animation anim = new ShowAnimation(mOutfitSelectSlider, (int) getResources().getDimension(R.dimen.outfit_selector_height));
        ViewGroup.LayoutParams p = mOutfitSelectSlider.getLayoutParams();
        p.height = 0;
        mOutfitSelectSlider.setLayoutParams(p);
        mOutfitSelectSlider.setVisibility(View.VISIBLE);

        mOutfitCreatorOpen = true;
        mOutfitSelectSlider.startAnimation(anim);
        mSelectorChevron.setRotation(0);
    }
}
