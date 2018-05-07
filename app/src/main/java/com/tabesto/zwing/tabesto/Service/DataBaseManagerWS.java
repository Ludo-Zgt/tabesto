package com.tabesto.zwing.tabesto.Service;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.tabesto.zwing.tabesto.Adapter.MyAdapter;
import com.tabesto.zwing.tabesto.Models.Meal;
import com.tabesto.zwing.tabesto.UI.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataBaseManagerWS extends AsyncTask<Void, Void, Void> {

    private String data = "";
    private String singleParsed = "";


    private List<Meal> meals = new ArrayList<>();

    private String[] prices = {"13€", "12€", "15€", "18€", "9€", "23€", "7€", "12€", "16€", "17€"};


    private DataBaseManagerWS() {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.v("TEST", "doInBackground");
        try {
            URL url = new URL("https://www.themealdb.com/api/json/v1/1/latest.php");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject jo = new JSONObject(data);



            JSONArray ja = new JSONArray();
            ja = jo.getJSONArray("meals");

            for (int i = 0; i<ja.length(); i++){

                jo = (JSONObject) ja.get(i);
                singleParsed = "{'strMeal':'"+jo.getString("strMeal")+"',"+
                        "'strCategory':'"+jo.getString("strCategory")+"',"+
                        //"'strArea':'"+jo.getString("strInstruction").replace("\"", "")+"',"+
                        "'strMealThumb':'"+jo.getString("strMealThumb")+"',"+
                        "'strArea':'"+jo.getString("strArea")+"',"+
                        "'strIngredient1':'"+jo.getString("strIngredient1")+"',"+
                        "'strIngredient2':'"+jo.getString("strIngredient2")+"',"+
                        "'strIngredient3':'"+jo.getString("strIngredient3")+"'}";



                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(singleParsed));
                reader.setLenient(true);
                Meal meal = gson.fromJson(reader, Meal.class);
                meal.setPrice(prices[i]);

                meals.add(meal);

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
        e.printStackTrace();
    }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.v("TEST", meals.get(0).toString());
        MyAdapter.meals = meals;
        MainActivity.showItems();
    }

    private static DataBaseManagerWS INSTANCE = new DataBaseManagerWS();

    public static DataBaseManagerWS getInstance()
    {   return INSTANCE;
    }



}