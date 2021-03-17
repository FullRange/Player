package com.oke.player.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oke.player.databinding.FragmentListBinding
import com.oke.player.ui.main.MainEffect
import com.oke.player.ui.main.MainViewModel
import com.oke.player.ui.main.list.widget.MovieListAdapter

class ListFragment : Fragment() {

    private val minLength = 3

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
        viewModel.effect.observe(viewLifecycleOwner, Observer { handleEffect(it) })
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initList()
    }

    private fun initSearch() {
        binding?.enterSearch?.addTextChangedListener {
            it?.let {
                if (it.length >= minLength) {
                    viewModel.onSearch(it.toString())
                } else {
                    viewModel.clearData()
                }
            }
        }
    }

    private fun initList() {
        binding?.movieList?.apply {
            adapter = MovieListAdapter(viewModel, this@ListFragment)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun handleEffect(effect: MainEffect) {
        when(effect) {
            MainEffect.GoToPlayer -> findNavController().navigate(ListFragmentDirections.navigateToPlayerView())
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}