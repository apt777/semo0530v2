package com.foodproject.activity;

import static com.foodproject.activity.PlacesListActivity.ARG_CATEGORY_NAME;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.foodproject.R;
import com.foodproject.adapter.CategoriesAdapter;
import com.foodproject.model.Category;

public class CategoriesListActivity extends AppCompatActivity implements CategoriesAdapter.OnCategoryClickListener{

    private RelativeLayout mBack;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        initialize();
        setupWidgets();
    }

    private void initialize() {
        mBack = findViewById(R.id.back);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void setupWidgets() {
        //change status bar color to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
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

        //setup product recycler view
        GridLayoutManager lnlGrid = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(lnlGrid);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this);
        mRecyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public void onCategoryClickListener(Category category) {
        Intent i = new Intent(CategoriesListActivity.this, PlacesListActivity.class);
        i.putExtra(ARG_CATEGORY_NAME, category.getCategoryName());
        startActivity(i);
    }
}
