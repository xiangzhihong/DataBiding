package com.xzh.databinding;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xzh.databinding.adapter.MovieAdapter;
import com.xzh.databinding.controller.MovieHttpManager;
import com.xzh.databinding.databinding.ActivityMovieBinding;
import com.xzh.databinding.model.Movie;

import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private ActivityMovieBinding binding=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        init();
    }

    private void init() {
        initTitle();
        initView();
    }

    private void initTitle() {
//        binding.toolbar.setTitle("电影");
//        setSupportActionBar(binding.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLUE);
        toolbar.setTitle("电影");
    }

    private void initView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchData("科幻电影");
            }
        });

        getSearchData("科幻电影");
    }

    private void getSearchData(String search) {
        binding.progressBar.setVisibility(View.VISIBLE);
        MovieHttpManager.searchMovies(search, new MovieHttpManager.IMovieResponse<List<Movie>>() {
            @Override
            public void onData(List<Movie> list) {
                MovieAdapter mAdapter = new MovieAdapter(MovieActivity.this, list);
                binding.recyclerView.setAdapter(mAdapter);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }


}
