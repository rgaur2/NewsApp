package com.example.rish.newsapp;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by rish on 6/21/2017.
 */

public class JSON {


    private static String title;
    private static String  description;

    private static String image;
    private  final static String STATUSPARAM = "status";
    public static  String ARTICLEPARAM = "articles";
    public static String TITLEPARAM = "title";
    public static String DESCPARAM = "description";
    public static String IMAGEPARAM = "urlToImage";


    public static JSONArray jsonNews(String jsondata)
    {
        try{


            JSONObject  newsdata = new  JSONObject(jsondata);

            JSONArray articaldata =  newsdata.getJSONArray( ARTICLEPARAM );

            return  articaldata;
        }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return null;

    }




}
