package com.tabesto.zwing.tabesto.Service;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabesto.zwing.tabesto.Exception.HttpRequestUnauthorizedException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataBaseManagerWS <T> {
    protected Context context;
    public static String PUT = "PUT";
    public static String POST = "POST";
    public static String PATCH = "PATCH";
    public static String DELETE = "DELETE";
    private String temp, temp2;

    private StringBuilder result;
    private ObjectMapper mapper = new ObjectMapper();
    private String uid;

    private HttpURLConnection urlConnection;

    public DataBaseManagerWS() {
    }

    public DataBaseManagerWS(String uid) {
        this.uid = uid;
    }


    public List<T> readWS(Class<T> type, TypeReference<List<T>> typeReference, URL url) throws HttpRequestUnauthorizedException {

        List<T> list = Collections.emptyList();
        result = new StringBuilder();
        try {

            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            temp= result.toString();


            list = mapper.readValue(temp, typeReference);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return list;
    }

}