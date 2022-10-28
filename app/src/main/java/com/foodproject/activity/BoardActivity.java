package com.foodproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodproject.R;
import com.foodproject.adapter.PostingAdapter;
import com.foodproject.model.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardActivity extends AppCompatActivity implements PostingAdapter.OnPostClickListener{

    private Context mContext = BoardActivity.this;
    private RecyclerView mPostRecycler;
    private PostingAdapter mPostingAdapter;
    private static final String TAG = "BoardActivity";
    private FloatingActionButton fab_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        /*
        Button imageButton = (Button) findViewById(R.id.btn_board_posting);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(intent);
            }
        });
         */



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.action_board);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_board:
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

        //플로팅버튼클릭시 게시판글쓰기
        FloatingActionButton fab_main=(FloatingActionButton) findViewById(R.id.fab_main);
        fab_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), BoardUploadActivity.class);
                startActivity(intent);
            }
        });

        //게시물 버튼리스너
        Button board1button=(Button) findViewById(R.id.board1);
        board1button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting.class);
                startActivity(intent);
            }
        });

        Button board2button=(Button) findViewById(R.id.board2);
        board2button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting1.class);
                startActivity(intent);
            }
        });

        Button board3button=(Button) findViewById(R.id.board3);
        board3button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting2.class);
                startActivity(intent);
            }
        });

        Button board4button=(Button) findViewById(R.id.board4);
        board4button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting3.class);
                startActivity(intent);
            }
        });

        Button board5button=(Button) findViewById(R.id.board5);
        board5button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting4.class);
                startActivity(intent);
            }
        });

        Button board6button=(Button) findViewById(R.id.board6);
        board6button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting5.class);
                startActivity(intent);
            }
        });

        Button board7button=(Button) findViewById(R.id.board7);
        board7button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting6.class);
                startActivity(intent);
            }
        });

        Button board8button=(Button) findViewById(R.id.board8);
        board8button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting7.class);
                startActivity(intent);
            }
        });

        Button board9button=(Button) findViewById(R.id.board9);
        board9button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting8.class);
                startActivity(intent);
            }
        });

        Button board10button=(Button) findViewById(R.id.board10);
        board10button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting9.class);
                startActivity(intent);
            }
        });

        Button board11button=(Button) findViewById(R.id.board11);
        board11button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting10.class);
                startActivity(intent);
            }
        });

        Button board12button=(Button) findViewById(R.id.board12);
        board12button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),BoardPosting11.class);
                startActivity(intent);
            }
        });

    }




    @Override
    public void onPostClickListener(Post post) {

    }
}
