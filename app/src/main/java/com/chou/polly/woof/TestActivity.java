package com.chou.polly.woof;

import android.os.Bundle;

public class TestActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        // init ...
        addGeneralFragment(new xxxxFragment());
    }

    @Override
    protected boolean keepToolBar() {
        return false;
    }
}
