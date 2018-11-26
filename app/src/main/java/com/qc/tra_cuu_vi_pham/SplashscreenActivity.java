package com.qc.tra_cuu_vi_pham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashscreenActivity.this,MainActivity.class));
        finish();
    }
}
