package com.example.cosmeticlistproject.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel
import com.example.cosmeticlistproject.util.StateResult

class MainActivity : BaseActivity<ActivityMainBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: ProductViewModel = ProductViewModel()
    private var currentPage = 1
    val productListAdapter by lazy {
        ProductListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initScrollView()
    }

    fun initView() {
        viewDataBinding.rvProductList.layoutManager = LinearLayoutManager(applicationContext)
        viewDataBinding.rvProductList.adapter = productListAdapter

        viewModel.getResult(currentPage)
        viewModel.productResponse.observe(this, Observer { response ->
            productListAdapter.addProducts(response.productList)
        })
    }

    fun initScrollView() {
        viewDataBinding.rvProductList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!viewDataBinding.rvProductList.canScrollVertically(1)) {
                    productListAdapter.deleteLoading()

                    if(viewModel.responseResult.value != StateResult.ERROR) {
                        viewModel.getResult(++currentPage)
                    }
                }
            }
        })
    }
}