package com.xzh.databinding.controller;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xzh.databinding.model.Movie;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by user on 2016/9/28.
 * Function
 */

public class MovieHttpManager {

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    public interface IMovieResponse<T> {
        void onData(T data);
    }

    public static void searchMovies(String name, final IMovieResponse<List<Movie>> response) {
        RequestParams params = new RequestParams();
        params.put("tag", name);
        params.put("start", 0);
        params.put("end", 50);
        client.get(getAbsoluteUrl("movie/search"), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    Gson gson = new Gson();
                    JSONObject json = new JSONObject(new String(bytes));
                    JSONArray jaMovies = json.optJSONArray("subjects");
                    List<Movie> movies = gson.fromJson(jaMovies.toString(), new TypeToken<List<Movie>>() {
                    }.getType());
                    response.onData(movies);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("onFailure",throwable.toString());
            }

        });
    }
}
