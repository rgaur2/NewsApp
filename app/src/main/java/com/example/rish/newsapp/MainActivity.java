package com.example.rish.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private TextView newsDescriptionView;
    private TextView newsTextsview;

    private TextView newsPublishview;
    private TextView newsAutsview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        newsTextsview = (TextView) findViewById(R.id.text_news_data);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

            NewsLoad();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

      getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;


    }




    public void NewsLoad()
    {
        String search = "the-next-web";
        String key = "0be0d1f63f974cd9b296e0afd94cbc26";
        NewsFetch netAysnc  = new NewsFetch();
        netAysnc.execute(search,key);

    }





     class NewsFetch  extends AsyncTask<String, Void, JSONArray> {
         @Override
         protected JSONArray doInBackground(String... params) {
             String search1 = params[0];
             String api = params[1];
             JSONObject jsonObj = null;
             JSONArray newsDataArray = null;

             URL url = NetworkUtils.buildUrl(search1,"latest",api);
             String result = null;

             try {
                 result = NetworkUtils.getResponseFromHttpUrl(url);
                 newsDataArray = JSON.jsonNews(result);


             } catch (Exception e) {
                 e.printStackTrace();
             }

             return newsDataArray;
         }

         @Override
         protected void onPostExecute(JSONArray newsDataArray) {
             if (newsDataArray != null) {
                progressBar.setVisibility(View.GONE);
                 for(int i =0;i< newsDataArray.length();i++)
                     try  {


                         JSONObject row = newsDataArray.getJSONObject(i);
                            newsTextsview.append(row.getString("title")+"\n\n");
                     }

                     catch (Exception e)
                     {
                         e.printStackTrace();
                     }
             }
         }


     }
}
