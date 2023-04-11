package com.example.mydeezer;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServicesApi {

    private  static String URL_API="https://api.deezer.com/search?q=";

    public static void musicsRequest(Context context, String search, IListenerApi listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API + search,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Music> musics= new ArrayList<Music>();
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("data");
                            for(int i=0; i<jsonArray.length(); i++){
                                Music temp= new Music();
                                JSONObject object= jsonArray.getJSONObject(i);
                                temp.setTitle(object.getString("title"));
                                temp.setArtist(object.getJSONObject("artist").getString("name"));
                                temp.setAlbum(object.getJSONObject("album").getString("title"));
                                temp.setCover(object.getJSONObject("album").getString("cover_medium"));
                                temp.setSongUrl(object.getString("preview"));
                                temp.setLink(object.getString("link"));
                                musics.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.onReceiveMusics(musics);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    public static void loadImage(Context context, String url, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                    }
                });
        queue.add(request);
    }

}