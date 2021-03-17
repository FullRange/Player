package com.oke.player.ui.main.list.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.oke.player.databinding.ItemMovieBinding
import com.oke.player.ui.main.list.MovieItem

private val diffUtil = object : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = false
}

class MovieListAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<MovieItem, ItemMovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false).apply {
            lifecycleOwner = this@MovieListAdapter.lifecycleOwner
        }

        return ItemMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) = holder.bind(getItem(position))

}