package com.example.cosmeticlistproject.ui.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class MainActivity : BaseActivity<ActivityMainBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: ProductViewModel  by lazy {
        ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(application)).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}