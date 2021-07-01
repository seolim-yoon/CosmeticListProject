package com.example.cosmeticlistproject.ui.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class MainActivity : BaseActivity<ActivityMainBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: ProductViewModel = ProductViewModel()
    private var currentPage = 1
    val productListAdapter by lazy {
        ProductListAdapter(this)
    }

    var recommendLists = ArrayList<ArrayList<Recommend>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initScrollView()
    }

    fun initView() {
        viewDataBinding.rvProductList.layoutManager = LinearLayoutManager(applicationContext)
        viewDataBinding.rvProductList.adapter = productListAdapter

        viewModel.getProductResult(currentPage)
        viewModel.getRecommendResult()
        viewModel.productResponse.observe(this, Observer { response ->
            productListAdapter.addProducts(response.productList)
        })
        viewModel.recommendResponse.observe(this, Observer { response ->
            recommendLists.add(response.recommendList1)
            recommendLists.add(response.recommendList2)
            recommendLists.add(response.recommendList3)
        })
    }

    fun initScrollView() {
        viewDataBinding.rvProductList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

//                    productListAdapter.addRecommends(recommendLists[lastVisibleItemPosition / 11], lastVisibleItemPosition)

                if(!viewDataBinding.rvProductList.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    productListAdapter.deleteLoading()
                    viewModel.getProductResult(++currentPage)
                }
            }
        })
    }
}