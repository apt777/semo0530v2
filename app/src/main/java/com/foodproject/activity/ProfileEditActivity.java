package com.foodproject.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.foodproject.R;

public class ProfileEditActivity extends AppCompatActivity {
    private RelativeLayout mBack;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        initialize();
        setupWidgets();
    }
    private void initialize() {
        mBack = findViewById(R.id.back);
    }
    private void setupWidgets() {
        //change status bar color to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.headerColor));
        }

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                Intent intent = new Intent();

                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}