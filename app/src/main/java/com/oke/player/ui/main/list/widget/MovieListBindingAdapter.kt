package com.oke.player.ui.main.list.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oke.player.ui.main.list.MovieItem

@BindingAdapter("movieList")
fun setMovieList(recyclerView: RecyclerView, list: List<MovieItem>?) =
    list?.let {
        (recyclerView.adapter as MovieListAdapter).submitList(list)
    }
