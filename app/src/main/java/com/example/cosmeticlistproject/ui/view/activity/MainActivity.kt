package com.example.cosmeticlistproject.ui.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel
import com.example.cosmeticlistproject.util.StateResult
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<ActivityMainBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: ProductViewModel = ProductViewModel()

    private var currentPage = 1
    private var recommendLists = ArrayList<ArrayList<Recommend>>()
    private val productListAdapter by lazy {
        ProductListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initScrollView()
    }

    fun initView() {
        with(viewDataBinding.rvProductList) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = productListAdapter
        }

        with(viewModel) {
            getProductResult(currentPage)
            getRecommendResult()

            productResponse.observe(this@MainActivity, Observer { response ->
                productListAdapter.addProducts(response.productList, currentPage)
            })

            recommendResponse.observe(this@MainActivity, Observer { response ->
                recommendLists.add(response.recommendList1)
                recommendLists.add(response.recommendList2)
                recommendLists.add(response.recommendList3)
                productListAdapter.addRecommends(recommendLists)
            })

            stateResult.observe(this@MainActivity, Observer { result ->
                when (result) {
                    StateResult.ERROR -> toast("Loading fail")
                }
            })
        }
    }

    fun initScrollView() {
        viewDataBinding.rvProductList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!viewDataBinding.rvProductList.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    productListAdapter.deleteLoading()
                    viewModel.getProductResult(++currentPage)
                }
            }
        })
    }
}