package com.example.cosmeticlistproject.ui.view

import android.os.Bundle
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResID: Int =
        R.layout.activity_main
    override val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}