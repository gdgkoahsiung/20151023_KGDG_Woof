package com.chou.polly.woof;

import android.os.Bundle;

public class TestFragment extends BaseFragment {
    @Override
    protected int getContainerLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuView() {
        return R.menu.xxx;
    }

    @Override
    protected boolean needLogin() {
        return true;
    }


    @Override
    protected void init() {
        super.init();

        // init GA ...
    }

    @Override
    protected void viewIsReady(Bundle savedInstanceState) {
        super.viewIsReady(savedInstanceState);

        // find view by id ...
    }

    @Override
    protected void prepareData() {
        super.prepareData();

        reloadData();
    }

    @Override
    protected void doEachOpen() {
        super.doEachOpen();

        reloadView();
    }

    @Override
    protected void reloadData() {
        super.reloadData();

        // call api ...
    }

    @Override
    protected void reloadView() {
        super.reloadView();

        // update view
    }
}
