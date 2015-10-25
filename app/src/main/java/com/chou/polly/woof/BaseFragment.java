package com.chou.polly.woof;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {
    // onCreate
    protected void init() {}

    // onCreateView
    protected abstract int getContainerLayoutId();

    // onViewCreated
    protected void viewIsReady(Bundle savedInstanceState) {}
    protected void prepareData() {}

    // onResume
    protected boolean needLogin() { return false; }
    protected int getToolBarTitle() { return R.string.app_name; }
    protected void doEachOpen() {
        if(needLogin() && !isLogin()) {
            Toast.makeText(activity, "須重新登入", Toast.LENGTH_SHORT).show();
            activity.finish();
        }
    }
    protected void reloadView() {}
    protected void reloadData() {}
    protected int getMenuView() {return R.menu.main;}

    protected LayoutInflater inflater;
    protected View baseView;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuView(), menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.baseView = inflater.inflate(getContainerLayoutId(), container, false);
        return baseView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewIsReady(savedInstanceState);
        prepareData();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getActivity().keepToolBar()){
            getActivity().getToolbar().setToolBarTitle(getToolBarTitle());
        }

        registerReceiver();
        doEachOpen();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideSoftKeyboard();
    }

    public boolean isActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusView = getActivity().getCurrentFocus();
        if(focusView != null) imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
    }
}
