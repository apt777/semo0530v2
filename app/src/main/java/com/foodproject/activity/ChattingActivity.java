package com.foodproject.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.foodproject.R;
import com.foodproject.adapter.SellerAdapter;
import com.foodproject.model.Place;

public class ChattingActivity extends AppCompatActivity implements SellerAdapter.OnPlaceClickListener{

    private Button mBtn;
    private ImageView mImage;
    private EditText mEdit1, mEdit2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout);

        initialize();
        setupWidgets();
    }

    private void initialize() {

    }

    private void setupWidgets() {

    }

    @Override
    public void onPlaceClickListener(Place place) {

    }

    @Override
    public void onPlaceFavoriteClick(Place place) {

    }
}
