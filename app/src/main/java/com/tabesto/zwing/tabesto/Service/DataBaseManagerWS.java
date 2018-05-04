package com.tabesto.zwing.tabesto.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabesto.zwing.tabesto.Exception.HttpRequestUnauthorizedException;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.tabesto.zwing.tabesto.Parcelable.Meal;

public class DataBaseManagerWS extends AsyncTask<String, Void, Void> {

    private String temp;
    private List<Meal> meals= new ArrayList<>();

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
            String[] res = temp.split("meals\":");
            Log.v("TEST", res[0]);

            meals = mapper.readValue(temp, typeReference);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //Log.v("TEST", meals.get(0).toString() );
    }

    /*private static DataBaseManagerWS INSTANCE = new DataBaseManagerWS();

    public static DataBaseManagerWS getInstance()
    {   return INSTANCE;
    }*/



}