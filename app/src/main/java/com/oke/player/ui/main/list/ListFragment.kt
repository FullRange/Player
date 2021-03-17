package com.oke.player.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oke.player.databinding.FragmentListBinding
import com.oke.player.ui.main.MainViewModel
import com.oke.player.ui.main.list.widget.MovieListAdapter

class ListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater).apply {
            viewModel = this@ListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding?.movieList?.apply {
            adapter = MovieListAdapter(this@ListFragment)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}