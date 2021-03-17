package com.oke.player.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.oke.player.databinding.FragmentPlayerBinding
import com.oke.player.ui.main.MainViewModel

class PlayerFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentPlayerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater).apply {
            viewModel = this@PlayerFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideo()
    }

    private fun initVideo() {
        binding?.videoArea?.apply {
            val mediaController = MediaController(this.context)
            mediaController.setAnchorView(this)
            setVideoPath(viewModel.getVideoUrl())
            setMediaController(mediaController)
            requestFocus()
            start()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}