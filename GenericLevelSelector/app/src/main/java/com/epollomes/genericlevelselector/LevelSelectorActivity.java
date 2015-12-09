package com.epollomes.genericlevelselector;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.epollomes.genericlevelselector.fragment.LevelSelectorFragment;
import com.epollomes.genericlevelselector.fragment.SinglePageFragment;

public class LevelSelectorActivity extends AppCompatActivity implements LevelSelectorFragment
        .IOnLevelSelectorFragmentInteractionListener, SinglePageFragment.IOnPageFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level_selector);
        FrameLayout rootFrameLayout = (FrameLayout) findViewById(R.id.root_frame_layout);

        rootFrameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        addLevelSelectorFragment();

    }


    private void addLevelSelectorFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_layout_id);
        if (fragment == null) {
            fragment = new LevelSelectorFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_layout_id, fragment, "fragment")
                    .commit();
        }
    }


    @Override
    public void on1stFunctionInLevelSelectorFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPageFragmentInteraction(Uri uri) {

    }
}
