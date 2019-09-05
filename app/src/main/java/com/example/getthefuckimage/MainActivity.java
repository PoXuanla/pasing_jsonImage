package com.example.getthefuckimage;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Move mv = new Move();
    public static String QrcodeResult = "1";
    public static String NAME ;
    public String qqq="";
    ImageLoader mImageLoader;
    NetworkImageView mImageView;
    TextView title,rating,releaseYear,genre;
      static String NNN ="";
    private static final String TAG = "Test";
    private static final String TAGG = "Catch";
    //接收 QRcode 字串 ，回傳json檔案 的 php
    private static final String url ="http://120.110.112.81:11111/SQL/showdata.php";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mImageView = (NetworkImageView) findViewById(R.id.networkImageView);
        title = (TextView)findViewById(R.id.titleName);
        rating = (TextView)findViewById(R.id.rating);
        releaseYear = (TextView)findViewById(R.id.releaseYear);
        genre = (TextView)findViewById(R.id.genre);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),NNN,Toast.LENGTH_SHORT).show();
            }
        });

        mImageLoader = MySingleton.getInstance(getApplication()).getImageLoader();
        //啟動QRcode掃描功能
        startActivity(new Intent(getApplicationContext(),ScanQrcode.class));

        GetJsonArray();
      //  Toast.makeText(this,NNN,Toast.LENGTH_LONG).show();
        //doSomething(NNN);

    }
//    protected void GetJsonArray() {
//        getString1(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//
//                try {
//                    //  Log.e("STRING", response);
//                    JSONArray jsonArray = new JSONArray(response);
//                    JSONObject jsonobject = jsonArray.getJSONObject(0);
//                    NNN = jsonobject.getString("name");
//                    Log.e("STRING", response + NNN);
//
//                    parseJson(jsonobject);
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e("error", "1");
//                }
//            }
//        });

//POST QRcode 字串 到 PHP ，拿回json檔案
//    protected void GetJsonArray() {
//        getString1(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//
//                try {
//
//                    JSONArray jsonArray = new JSONArray(response);
//                    JSONObject jsonobject = jsonArray.getJSONObject(0);
//
////
//                    parseJson(jsonobject);
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e("error", "1");
//                }
//            }
//        });
protected void GetJsonArray() {
          StringRequest movieReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                    NNN = response;
                    JSONArray jsonArray = new JSONArray(response);

                    JSONObject jsonobject = jsonArray.getJSONObject(0);

                    parseJson(jsonobject);

                }
                catch (Exception e){
                    Log.d(TAGG,e.toString());
                }

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
          }){
              @Override
              protected Map<String,String> getParams() {
                  Map<String,String> params = new HashMap<>();
                  params.put("name",QrcodeResult);
                  return params;
              }
          };
        MySingleton.getInstance(this).addToRequestQueue(movieReq);//把網路請求加到列隊中

    }

//把json檔案呈現在頁面上
    protected void parseJson(JSONObject jsonObject) throws JSONException {
        title.setText(jsonObject.getString("name"));
        NAME = jsonObject.getString("name");
        qqq = jsonObject.getString("name");
        //NNN = jsonObject.getString("name");
      //  mv.setThumbnailUrl(jsonObject.getString("name"));
        mImageView.setImageUrl(jsonObject.getString("image"), mImageLoader);
        rating.setText(jsonObject.getString("protein"));
        releaseYear.setText(jsonObject.getString("Vitamin"));
        genre.setText(jsonObject.getString("heat"));
    }

    public void getString1(final VolleyCallback callback) {
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //callback.onSuccess(response);
                NNN=response;
//                Toast.makeText(MainActivity.this,NNN,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("STRING", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
//                HashMap<String, String> p = new HashMap<String, String>();
//                p.put("account", "account");
//                p.put("password", "PWD");
//                p.put("code", "123");
//                return p;
                Map<String,String> params = new HashMap<>();
                params.put("name",QrcodeResult);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq);
    }
    public interface VolleyCallback{
        void onSuccess(String result);
    }
}