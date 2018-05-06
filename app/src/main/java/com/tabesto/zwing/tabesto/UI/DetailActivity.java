package com.tabesto.zwing.tabesto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tabesto.zwing.tabesto.Adapter.MyAdapter;
import com.tabesto.zwing.tabesto.Models.Meal;
import com.tabesto.zwing.tabesto.R;

public class DetailActivity extends AppCompatActivity {

    /**
     * Context
     */
    private Context ctx = this;
    /**
     * Model
     */
    private Meal meal;

    /**
     * Views
     */
    private ImageView image;
    private TextView tvName;
    private TextView tvCategory;
    private TextView tvArea;
    private TextView tvIngredients;
    private TextView tvPrice;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();

        Intent intent = getIntent();
        meal = (Meal)intent.getSerializableExtra(MyAdapter.TAG);

        setupViews();




    }

    private void initViews(){
        image = findViewById(R.id.imageview);
        tvName = findViewById(R.id.tv_name_detail);
        tvCategory = findViewById(R.id.tv_category_detail);
        tvArea = findViewById(R.id.tv_area_detail);
        tvIngredients = findViewById(R.id.tv_ingredients_detail);

        tvPrice = findViewById(R.id.tv_price_detail);
        btnOrder = findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Your order as been send !", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupViews(){

        Log.v("TEST", meal.toString());

        Picasso.with(image.getContext()).load(meal.getStrMealThumb()).centerCrop().fit().into(image);
        tvName.setText(meal.getStrMeal());
        tvCategory.setText(meal.getStrCategory());
        tvArea.setText(meal.getStrArea());
        String strIngredients = "Ingredients :\n     - "+ meal.getStrIngredient1()
                +"\n     - "+ meal.getStrIngredient2()
                +"\n     - "+ meal.getStrIngredient3();
        tvIngredients.setText(strIngredients);
        tvPrice.setText(meal.getPrice());

    }
}
