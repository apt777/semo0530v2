package com.foodproject.activity;

import static com.foodproject.activity.PlacesListActivity.ARG_CATEGORY_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodproject.R;
import com.foodproject.adapter.CustomAdapter;
import com.foodproject.adapter.HomeCategoriesAdapter;
import com.foodproject.adapter.SellerAdapter;
import com.foodproject.model.Category;
import com.foodproject.model.Food;
import com.foodproject.model.Place;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements
        SellerAdapter.OnPlaceClickListener,HomeCategoriesAdapter.OnCategoryClickListener,View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter FB_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Food> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private RecyclerView mCategoryRecycler, mPlaceRecycler;
    private HomeCategoriesAdapter mCategoriesAdapter;

    private TextView mCategoriesList;
    private ActionBar actionBar;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab_main, fab1, fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.place_recycler_view_main); //아이디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // Food 객체를 담을 어레이 리스트 (어댑터 쪽으로 날림)
        
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        
        databaseReference = database.getReference("Foods"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않도록 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List 추출
                    Food user = snapshot.getValue(Food.class); // 만들어둔 Food 객체에 데이터를 담는다.
                    arrayList.add(user); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                FB_adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던 중 에러 발생 시
                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        FB_adapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(FB_adapter); // 리사이클러뷰에 어댑터 연결


        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_main = (FloatingActionButton) findViewById(R.id.fab_main);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab_main.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
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
                    Intent pageIntent=new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(pageIntent);
                }
                if(id==R.id.action_category){
                    Intent categoryIntent=new Intent(MainActivity.this, CategoriesListActivity.class);
                    startActivity(categoryIntent);
                }
                if(id==R.id.action_gesi){
                    Intent categoryIntent=new Intent(MainActivity.this, BoardActivity.class);
                    startActivity(categoryIntent);
                }
                if(id==R.id.action_sise) {
                    Intent categoryIntent = new Intent(MainActivity.this, ChartActivity.class);
                    startActivity(categoryIntent);
                }
                if (id==R.id.action_repo) {
                    Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                    startActivity(reportIntent);
                }
                return MainActivity.super.onOptionsItemSelected(item);
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
    //플로팅아이콘 클릭시
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_main:
                anim();
                break;
            case R.id.fab1:
                anim();
                Intent intent1 = new Intent(getApplicationContext(), FoodUploadActivity.class);
                startActivity(intent1);
                break;
            case R.id.fab2:
                anim();
                Intent intent2 = new Intent(getApplicationContext(), FavoriteItemsActivity.class);
                startActivity(intent2);
                break;
        }


    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
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




        mCategoriesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoriesListActivity.class));
            }
        });
    }


    public void onCategoryClickListener(Category category) {
        Intent i = new Intent(MainActivity.this, PlacesListActivity.class);
        i.putExtra(ARG_CATEGORY_NAME, category.getCategoryName());
        startActivity(i);
    }


    public void onPlaceClickListener(Place place) {
        startActivity(new Intent(MainActivity.this, PlaceActivity.class));
    }


    public void onPlaceFavoriteClick(Place place) {

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