package com.oke.player.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oke.player.R
import com.oke.player.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel>

    private val mainViewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>) = viewModelProvider.get() as T
        }
    }

    private var binding: ActivityMainBinding? = null

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewModel = this@MainActivity.mainViewModel
                lifecycleOwner = this@MainActivity
            }
        navController = findNavController(R.id.main_fragment)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}