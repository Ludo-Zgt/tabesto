package com.tabesto.zwing.tabesto.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabesto.zwing.tabesto.Parcelable.Meal;
import com.tabesto.zwing.tabesto.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private Context ctx = this;

    protected String URL = "https://www.themealdb.com/api/json/v1/1/latest.php";
    private List<Meal> meals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllMeals();

    }


    private void getAllMeals(){
        new DataBaseManagerWS().execute(URL);
    }

    private void showItems(){
        //Toast.makeText(ctx, meals.get(0).toString(), Toast.LENGTH_LONG);
    }

    public class DataBaseManagerWS extends AsyncTask<String, Void, Void> {

        private String temp;


        private TypeReference<List<Meal>> typeReference = new TypeReference<List<Meal>>() {
        };
        private StringBuilder result;
        private ObjectMapper mapper = new ObjectMapper();


        private HttpURLConnection urlConnection;

        public DataBaseManagerWS() {
        }

        @Override
        protected Void doInBackground(String... params) {

            result = new StringBuilder();
            try {

                urlConnection = (HttpsURLConnection) new URL(params[0]).openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                temp = result.toString();
                Log.v("TEST", temp);
                //String[] res = temp.split("meals\":");
                //Log.v("TEST", res[1]);

                meals = mapper.readValue(temp, typeReference);

            } catch (Exception e) {
                e.printStackTrace();
                meals = Collections.emptyList();
            } finally {
                urlConnection.disconnect();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //Log.v("TEST", meals.get(0).toString() );
            if(meals.get(0)!=null){
                Toast.makeText(ctx, meals.get(0).toString(), Toast.LENGTH_LONG).show();
            }

        }

    /*private static DataBaseManagerWS INSTANCE = new DataBaseManagerWS();

    public static DataBaseManagerWS getInstance()
    {   return INSTANCE;
    }*/



    }
}
