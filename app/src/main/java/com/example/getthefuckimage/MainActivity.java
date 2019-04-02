package com.example.getthefuckimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageLoader mImageLoader;
    NetworkImageView mImageView;
    // The URL for the image that is being loaded.
    private static final String url =
            "https://api.androidhive.info/json/movies.json";
    String aa;
    String urll = "http://i.imgur.com/7spzG.png";
    Move movie = new Move();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (NetworkImageView)findViewById(R.id.networkImageView);

        JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               try {
                        JSONObject obj = response.getJSONObject(0);
                        aa = obj.getString("title");
                   Toast.makeText(getApplication(),aa,
                           Toast.LENGTH_SHORT).show();
                    movie.setThumbnailUrl(obj.getString("image"));

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "eee",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "默认Toast样式",
                        Toast.LENGTH_SHORT).show();
            }
        });


        MySingleton.getInstance(this).addToRequestQueue(movieReq);

        mImageLoader = MySingleton.getInstance(getApplication()).getImageLoader();

        mImageView.setImageUrl(urll, mImageLoader);


    }
}