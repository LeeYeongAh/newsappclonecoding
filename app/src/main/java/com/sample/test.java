package com.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test extends AppCompatActivity {
    public static RequestQueue requestQueue;

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.TextView_get);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();

            }
        });

        if(requestQueue != null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        } //RequestQueue 생성
    }

    public void sendRequest() {



            String keyword = "뉴스";
            String address = "https://dapi.kakao.com/v2/search/image?query=" + keyword;




        StringRequest request = new StringRequest(
                Request.Method.GET,
                address,
                new Response.Listener<String>() { //응답을 잘 받았을 때 이 메소드가 자동으로 호출

                    @Override
                    public void onResponse(String response) {
                        println(response);
                        try {

                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");
                            if(arrayArticles == null || arrayArticles.length() == 0) {println("array is null");}
                            else {
                                println("응답 -> " + arrayArticles);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {

            String combined = "Authorization" + ":" + "74cc11e28d4031d5f98a7d987cc588c9";

            // Base64 encode the string
            String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

            @Override
            public Map getHeaders() throws AuthFailureError {
                Map  params = new HashMap();


                return params;
            }
        };

        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
        requestQueue.add(request);
        println("요청 보냄.");

    }

    public void println(String data) {
        textView.setText(data +"\n");
    }
}