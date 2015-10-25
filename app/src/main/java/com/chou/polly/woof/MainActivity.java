package com.chou.polly.woof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BuildConfig.TYPE.equals("xiaomi")) {
            Toast.makeText(this, "不要秀！不要秀！不要秀！", Toast.LENGTH_SHORT).show();
        }

        if(BuildConfig.USE_TEST_DB) {
            Log.i("Woof", "res = ...");
        }

        int[] items = new int[]{1,2,3};
        // for
        // toast
        // log

        CustomBotton btn = new CustomBotton(this);
        btn.setBackgroundColor(CustomBotton.ButtonColor.blue);
    }
}
