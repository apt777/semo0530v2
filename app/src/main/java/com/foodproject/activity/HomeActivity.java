package com.foodproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.foodproject.R;
import com.foodproject.adapter.HomeCategoriesAdapter;
import com.foodproject.adapter.SellerAdapter;
import com.foodproject.model.Category;
import com.foodproject.model.Place;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import static com.foodproject.activity.PlacesListActivity.ARG_CATEGORY_NAME;

public class HomeActivity extends AppCompatActivity implements SellerAdapter.OnPlaceClickListener,
        HomeCategoriesAdapter.OnCategoryClickListener{

    private RecyclerView mCategoryRecycler, mPlaceRecycler;
    private HomeCategoriesAdapter mCategoriesAdapter;
    private SellerAdapter mSellerAdapter;
    private TextView mCategoriesList;
    private ActionBar actionBar;
    private Context mContext = HomeActivity.this;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab_main, fab1, fab2;

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setupWidgets();

        //사이드메뉴 리스너
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 햄버거 버튼 이미지 불러오기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        //사이드바 메뉴 클릭시 화면이동 인텐트 이벤트코드
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.action_my){
                    Intent pageIntent=new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(pageIntent);
                }
                if(id==R.id.action_category){
                    Intent categoryIntent=new Intent(HomeActivity.this, CategoriesListActivity.class);
                    startActivity(categoryIntent);
                }
                if(id==R.id.action_gesi){
                    Intent categoryIntent=new Intent(HomeActivity.this, BoardActivity.class);
                    startActivity(categoryIntent);
                }
                if(id==R.id.action_sise) {
                    Intent categoryIntent = new Intent(HomeActivity.this, ChartActivity.class);
                    startActivity(categoryIntent);
                }
                if (id==R.id.action_repo) {
                    Intent reportIntent = new Intent(HomeActivity.this, ReportActivity.class);
                    startActivity(reportIntent);
                }
                return HomeActivity.super.onOptionsItemSelected(item);
            }
        });

        //바텀네비게이션
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.action_home:
                        return true;
                    case R.id.action_board:
                        startActivity(new Intent(getApplicationContext(),BoardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_chart:
                        startActivity(new Intent(getApplicationContext(),ChartActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }



    private void initComponents() {
        mCategoryRecycler = findViewById(R.id.trending_recycler_view_main);
        mPlaceRecycler = findViewById(R.id.place_recycler_view_main);
        mCategoriesList = findViewById(R.id.categories_list);
    }

    private void setupWidgets() {

        LinearLayoutManager llmTrending = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCategoryRecycler.setLayoutManager(llmTrending);
        mCategoriesAdapter = new HomeCategoriesAdapter(this);
        mCategoryRecycler.setAdapter(mCategoriesAdapter);

        LinearLayoutManager llmPlace = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPlaceRecycler.setLayoutManager(llmPlace);
        mSellerAdapter = new SellerAdapter(this);
        mPlaceRecycler.setAdapter(mSellerAdapter);



        mCategoriesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CategoriesListActivity.class));
            }
        });
    }

    @Override
    public void onCategoryClickListener(Category category) {
        Intent i = new Intent(HomeActivity.this, PlacesListActivity.class);
        i.putExtra(ARG_CATEGORY_NAME, category.getCategoryName());
        startActivity(i);
    }

    @Override
    public void onPlaceClickListener(Place place) {
        startActivity(new Intent(HomeActivity.this, PlaceActivity.class));
    }

    @Override
    public void onPlaceFavoriteClick(Place place) {
        mSellerAdapter.setFavorite(place.getPlaceId());
        mSellerAdapter.notifyDataSetChanged();

    }

    //이전 버튼클릭시 사이드메뉴 닫기
    @Override
    public void onBackPressed(){
        DrawerLayout drawer=findViewById(R.id.drawerLayout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    //메뉴연동
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_up, menu);
        return true;
    }

}


