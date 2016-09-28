package com.xzh.databinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.xzh.databinding.R;
import com.xzh.databinding.databinding.MovieItemBinding;
import com.xzh.databinding.BR;
import com.xzh.databinding.model.Movie;

import java.util.List;

/**
 * Created by user on 2016/9/28.
 * Function
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {

    private List<Movie> mMovieModels;
    private Context mContext=null;

    private final TypedValue mTypedValue = new TypedValue();


    public class BindingHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding binding;

        public BindingHolder(View v) {
            super(v);
        }

        public MovieItemBinding getBinding() {
            return binding;
        }

        public void setBinding(MovieItemBinding binding) {
            this.binding = binding;
        }
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        mMovieModels = movies;
        mContext=context;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        MovieItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.movie_item,parent,false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Movie movie = mMovieModels.get(position);
        Glide.with(mContext)
                .load(movie.getImages().getMedium())
                .fitCenter()
                .into(holder.binding.ivMovie);
        holder.binding.setVariable(BR.movie, movie);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovieModels.size();
    }
}
