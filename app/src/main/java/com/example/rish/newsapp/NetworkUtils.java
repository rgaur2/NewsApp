package com.example.rish.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rish on 6/21/2017.
 */

public class NetworkUtils {



    private static final String BASE_URL = "https://newsapi.org/v1/articles";
   private  static final String QUERY_PARAM = "source";
    private   static final String SORT_PARAM = "sortBY";
    private static final String API_KEY = "apiKey";


    public static URL buildUrl(String parquery ,String sortquery,String apikey)
    {
        Uri uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter( QUERY_PARAM ,  parquery)
                .appendQueryParameter(SORT_PARAM , sortquery).appendQueryParameter( API_KEY, apikey).build();

        URL url = null;


        try {

            url = new URL(uri.toString());
        }
            catch (MalformedURLException e) {


                e.printStackTrace();
            }

       return url;


        }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
