package com.foodproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodproject.JSONPlaceholder;
import com.foodproject.R;
import com.foodproject.adapter.MyAdapter;
import com.foodproject.model.Chart;
import com.foodproject.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChartActivity extends AppCompatActivity {

    final String TAG = "ChartActivity";
    private String requestUrl;
    ArrayList<Chart> list = null;
    Chart foods = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        recyclerView = (RecyclerView) findViewById(R.id.it_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        AsyncTask
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

    }


    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList&p_product_cls_code=02&p_country_code=1101&p_regday=2015-01-01&p_convert_kg_yn=N&p_item_category_code=200&p_cert_key=cf285a66-75e8-42e3-9dc1-fe924aab53c1&p_cert_id=kjv5547&p_returntype=xml";
            try {
                boolean f_item_name = false;
                boolean f_kind_name = false;
                boolean f_rank = false;
                boolean f_unit = false;
                boolean f_day1 = false;
                boolean f_dpr1 = false;
                boolean f_day2 = false;
                boolean f_dpr2 = false;
                boolean f_day3 = false;
                boolean f_dpr3 = false;
                boolean f_day4 = false;
                boolean f_dpr4 = false;
                boolean f_day5 = false;
                boolean f_dpr5 = false;
                boolean f_day6 = false;
                boolean f_dpr6 = false;
                boolean f_day7 = false;
                boolean f_dpr7 = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Chart>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item") && foods != null) {
                                list.add(foods);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                foods = new Chart();
                            }
                            if (parser.getName().equals("item_name")) f_item_name = true;
                            if (parser.getName().equals("kind_name")) f_kind_name = true;
                            if (parser.getName().equals("rank")) f_rank = true;
                            if (parser.getName().equals("unit")) f_unit = true;
                            if (parser.getName().equals("day1")) f_day1 = true;
                            if (parser.getName().equals("dpr1")) f_dpr1 = true;
                            if (parser.getName().equals("day2")) f_day2 = true;
                            if (parser.getName().equals("dpr2")) f_dpr2 = true;
                            if (parser.getName().equals("day3")) f_day3 = true;
                            if (parser.getName().equals("dpr3")) f_dpr3 = true;
                            if (parser.getName().equals("day4")) f_day4 = true;
                            if (parser.getName().equals("dpr4")) f_dpr4 = true;
                            if (parser.getName().equals("day5")) f_day5 = true;
                            if (parser.getName().equals("dpr5")) f_dpr5 = true;
                            if (parser.getName().equals("day6")) f_day6 = true;
                            if (parser.getName().equals("dpr6")) f_dpr6 = true;
                            if (parser.getName().equals("day7")) f_day7 = true;
                            if (parser.getName().equals("dpr7")) f_dpr7 = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (f_item_name) {
                                foods.setItem_name(parser.getText());
                                f_item_name = false;
                            } else if (f_kind_name) {
                                foods.setKind_name(parser.getText());
                                f_kind_name = false;
                            } else if (f_rank) {
                                foods.setRank(parser.getText());
                                f_rank = false;
                            } else if (f_unit) {
                                foods.setUnit(parser.getText());
                                f_unit = false;
                            } else if (f_day1) {
                                foods.setDay1(parser.getText());
                                f_day1 = false;
                            } else if (f_dpr1) {
                                foods.setDpr1(parser.getText());
                                f_dpr1 = false;
                            } else if (f_day2) {
                                foods.setDay2(parser.getText());
                                f_day2 = false;
                            } else if (f_dpr2) {
                                foods.setDpr2(parser.getText());
                                f_dpr2 = false;
                            } else if (f_day3) {
                                foods.setDay3(parser.getText());
                                f_day3 = false;
                            } else if (f_dpr3) {
                                foods.setDpr3(parser.getText());
                                f_dpr3 = false;
                            } else if (f_day4) {
                                foods.setDay4(parser.getText());
                                f_day4 = false;
                            } else if (f_dpr4) {
                                foods.setDpr4(parser.getText());
                                f_dpr4 = false;
                            } else if (f_day5) {
                                foods.setDay5(parser.getText());
                                f_day5 = false;
                            } else if (f_dpr5) {
                                foods.setDpr5(parser.getText());
                                f_dpr5 = false;
                            } else if (f_day6) {
                                foods.setDay6(parser.getText());
                                f_day6 = false;
                            } else if (f_dpr6) {
                                foods.setDpr6(parser.getText());
                                f_dpr6 = false;
                            } else if (f_day7) {
                                foods.setDay7(parser.getText());
                                f_day7 = false;
                            } else if (f_dpr7) {
                                foods.setDpr7(parser.getText());
                                f_dpr7 = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
            MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);

            initComponents();
            setupWidgets();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
            bottomNavigationView.setSelectedItemId(R.id.action_chart);
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
                            return true;
                        case R.id.action_profile:
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                    }
                    return false;
                }
            });

        }

        private void initComponents() {

        }

        private void setupWidgets() {

        }

    }
}