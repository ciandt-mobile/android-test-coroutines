package com.ciandt.testcoroutines.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ciandt.testcoroutines.R
import com.ciandt.testcoroutines.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        btnUp.setOnClickListener { viewModel.up() }
        btnDown.setOnClickListener { viewModel.down() }
    }
}
