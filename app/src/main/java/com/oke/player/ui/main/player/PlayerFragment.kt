package com.oke.player.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.oke.player.databinding.FragmentPlayerBinding
import com.oke.player.ui.main.MainViewModel

class PlayerFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentPlayerBinding.inflate(inflater).apply {
            viewModel = this@PlayerFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return view.root
    }
}