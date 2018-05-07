package com.tabesto.zwing.tabesto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tabesto.zwing.tabesto.R;
import java.util.List;

public class IngredientAdapter extends ArrayAdapter<String> {

    //tweets est la liste des models à afficher
    public IngredientAdapter(Context context, List<String> ingredients) {
        super(context, 0, ingredients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_layout,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.tvIngredient = (TextView) convertView.findViewById(R.id.tv_ingredient);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        String ingredient = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.tvIngredient.setText(ingredient);


        return convertView;
    }

    private class TweetViewHolder{
        public TextView tvIngredient;

    }
}