package com.tabesto.zwing.tabesto.UI;


import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;


import com.tabesto.zwing.tabesto.Adapter.MyAdapter;
import com.tabesto.zwing.tabesto.Models.Meal;
import com.tabesto.zwing.tabesto.R;
import com.tabesto.zwing.tabesto.Service.DataBaseManagerWS;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Context
    private Context ctx = this;

    //Models
    public List<Meal> meals;

    //Data
    public static String data = "";

    //Views
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LayoutAnimationController animation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        meals = new ArrayList<>();

        buildRecyclerView();
        loadData();


    }

    @Override
    protected void onResume() {
        super.onResume();
        showItems();
    }

    private void buildRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), R.anim.layout_animation);

        adapter = new MyAdapter(ctx);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
        recyclerView.scheduleLayoutAnimation();
    }

    private void loadData(){
        Log.v("TEST", "loadData()");
        DataBaseManagerWS.getInstance().execute();
    }

    public static void showItems(){



        adapter.notifyDataSetChanged(); //not working
        //adapter = new MyAdapter(meals);
        //recyclerView.setAdapter(adapter);

        recyclerView.scheduleLayoutAnimation();

    }







}
