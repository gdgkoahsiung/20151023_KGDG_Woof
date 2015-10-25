package com.chou.polly.woof;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void init(Bundle savedInstanceState);
    protected int getContainerView() { return R.layout.activity_general; }
    protected boolean keepToolBar() { return true;}

    private int fragmentStack;
    protected FragmentManager fragmentManager;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();

        setupBackResumeListener();
        initView();
        init(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setToolbarTitle(int title) {
        toolBar.setTitle(title);
    }

    public void addGeneralFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commitAllowingStateLoss();
    }

    public void setupToolBar() {
        RelativeLayout view = (RelativeLayout) findViewById(R.id.toolbar);
        toolBar = new Toolbar(this);
        setSupportActionBar(toolBar);
        view.addView(toolBar);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Config.PAGE_TAG_BROWSE:
                    // open ...
                    break;
                case Config.PAGE_TAG_CART:
                    // open ...
                    break;
            }
        }
    }

    private void initView(){
        setContentView(getContainerView());
        if (keepToolBar()) {
            setupToolBar();
        } else {
            RelativeLayout toolBarView = (RelativeLayout) findViewById(R.id.toolbar);
            toolBarView.setVisibility(View.GONE);
        }
    }

    private void setupBackResumeListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager manager = activity.getSupportFragmentManager();
                if (manager.getBackStackEntryCount() > 0 && fragmentStack > manager.getBackStackEntryCount()) {
                    Fragment fragment = manager.getFragments().get(manager.getBackStackEntryCount() - 1);
                    if (fragment != null) fragment.onResume();
                }

                fragmentStack = manager.getBackStackEntryCount();
            }
        });
    }
}
