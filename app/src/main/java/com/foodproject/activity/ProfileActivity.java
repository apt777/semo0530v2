package com.foodproject.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.foodproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout favoriteRestaurants, favoriteFoods, logouts,reports;
    private TextView tv_result, tv_email;
    private CircularImageView iv_profile;

    private FirebaseAuth mFirebaseAuth;

    private Context mContext = ProfileActivity.this;
    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();

        ImageButton Logout = findViewById(R.id.LogoutButton);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut(); //로그아웃

                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button Revoke = findViewById(R.id.revokeButton);
        Revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.getCurrentUser().delete(); //탈퇴

                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        initComponents();
        setupWidgets();

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");
        String userEmail = intent.getStringExtra("userEmail");

        tv_result = findViewById(R.id.search_text);
        tv_result.setText(nickName);

        tv_email = findViewById(R.id.profile_email);
        tv_email.setText(userEmail);

        iv_profile = findViewById(R.id.profile_image);
        Glide.with(this).load(photoUrl).into(iv_profile);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_board:
                        startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_chart:
                        startActivity(new Intent(getApplicationContext(), ChartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_profile:
                        return true;
                }
                return false;
            }

        });


    }


    private void initComponents() {
        favoriteRestaurants = findViewById(R.id.favorite_restaurants);
        favoriteFoods = findViewById(R.id.favorite_foods);
        logouts = findViewById(R.id.logout);
        reports = findViewById(R.id.reports);
    }

    private void setupWidgets() {
        favoriteRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, ChattingActivity.class);
                startActivity(i);
            }
        });
        favoriteFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, FavoriteItemsActivity.class);
                startActivity(i);
            }
        });
        logouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, ReportActivity.class);
                startActivity(i);
            }
        });
    }
}
