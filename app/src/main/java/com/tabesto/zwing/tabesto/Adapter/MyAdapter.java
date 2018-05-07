package com.tabesto.zwing.tabesto.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tabesto.zwing.tabesto.Models.Meal;
import com.tabesto.zwing.tabesto.R;
import com.tabesto.zwing.tabesto.UI.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    /**
     * Activity Context
     */
    private Context ctxActivity;

    /**
     * List items
     */
    public static List<Meal> meals = new ArrayList<>();

    /**
     * Tag
     */
    public static String TAG = "meal";

    /**
     * Constructor MyAdapter
     * @param ctx : activity context
     */
    public MyAdapter(Context ctx){
        this.ctxActivity = ctx;
    }


    /**
     * Get the size of items in adapter
     * @return the size of the list of meals
     */
    @Override
    public int getItemCount() {
        return meals.size();
    }

    /**
     * Create View Holder by Type
     * @param parent : the view parent
     * @param viewType : the type of View
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.display(meal);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final ImageView image;



        public MyViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.v("TEST", meals.get(getAdapterPosition()).toString());
                    Intent intent = new Intent(ctxActivity, DetailActivity.class);
                    intent.putExtra(TAG,meals.get(getAdapterPosition()));
                    ctxActivity.startActivity(intent);

                }
            });
        }

        public void display(Meal meal) {
            name.setText(meal.getStrMeal());
            description.setText(meal.getPrice());
            //display the picture from URL
            Picasso.with(image.getContext()).load(meal.getStrMealThumb()).centerCrop().fit().into(image);


        }
    }


}
