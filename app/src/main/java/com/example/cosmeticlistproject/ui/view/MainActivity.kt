package com.example.cosmeticlistproject.ui.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class MainActivity : BaseActivity<ActivityMainBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: ProductViewModel = ProductViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getResult()
        viewModel.productResponse.observe(this, Observer { response ->
            Log.d("seolim", "${response!!.productsList}")

            viewDataBinding.rvProductList.adapter = ProductListAdapter(this, response!!.productsList)
            viewDataBinding.rvProductList.layoutManager = LinearLayoutManager(applicationContext)
        })
    }
}