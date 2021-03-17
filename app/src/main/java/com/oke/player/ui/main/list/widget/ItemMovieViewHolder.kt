package com.oke.player.ui.main.list.widget

import androidx.recyclerview.widget.RecyclerView
import com.oke.player.databinding.ItemMovieBinding
import com.oke.player.ui.main.list.MovieItem

class ItemMovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieItem?) = binding.run {
        if (item != null) {
            this.item = item
            executePendingBindings()
        }
    }
}