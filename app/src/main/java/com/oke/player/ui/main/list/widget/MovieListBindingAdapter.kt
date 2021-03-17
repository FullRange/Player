package com.oke.player.ui.main.list.widget

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oke.player.ui.main.list.MovieItem

@BindingAdapter("movieList")
fun setMovieList(recyclerView: RecyclerView, list: List<MovieItem>?) =
    list?.let {
        (recyclerView.adapter as MovieListAdapter).submitList(list)
    }

@BindingAdapter("url")
fun setUrl(image: ImageView, url: String?) =
    url?.let {
        Glide.with(image.context)
            .load(it)
            .into(image)
    }
