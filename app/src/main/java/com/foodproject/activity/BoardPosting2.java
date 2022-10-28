package com.foodproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BoardPosting2 extends AppCompatActivity {
    private LinearLayout favoriteRestaurants, favoriteFoods, logouts;

    private Context mContext = BoardPosting2.this;
    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_posting2);


        Intent intent = getIntent();
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

        Button moklok=(Button) findViewById(R.id.moklok);
        moklok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardActivity.class);
                startActivity(intent);
            }
        });
    }
}

