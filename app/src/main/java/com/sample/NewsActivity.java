package com.sample;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset = {"1","2"};

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_news);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        String a = "a";
        queue = Volley.newRequestQueue(this);
        Log.d("NEWS",a );
        getNews();
        /*
        * 1. 화면로딩 -> 뉴스정보 받아옴
        * 2. 정보 -> 어뎁터에 넘겨줌
        * 3. 어뎁터 -> 세팅
        */



    }
    public void getNews(){
        // Instantiate the RequestQueue.

        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=b747e34da8e146ceb783eba55a46dd62";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("NEWS1", response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            //response -> newsdata class 분류
                            Log.d("NEWS2", response);
                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");
                            Log.d("NEWS3", response);
                            List<NewsData> news = new ArrayList<>();
                            Log.d("NEWS4", response);
                            for(int i =0, j=arrayArticles.length(); i<j; i++){
                                JSONObject obj = arrayArticles.getJSONObject(i);
                                Log.d("NEWS", obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setContent(obj.getString("description"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                news.add(newsData);
                            }

                            mAdapter = new MyAdapter(news, NewsActivity.this);
                            mRecyclerView.setAdapter(mAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("에러 -> "+error.getMessage());
            }
        }




        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");


                return headers;
        }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);



    }

}