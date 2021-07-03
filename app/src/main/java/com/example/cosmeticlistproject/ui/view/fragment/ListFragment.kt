package com.example.cosmeticlistproject.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.FragmentListBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class ListFragment: BaseFragment<FragmentListBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_list
    override val viewModel: ProductViewModel = ProductViewModel()

    lateinit var navController: NavController
    private var currentPage = 1
    private var recommendLists = ArrayList<ArrayList<Recommend>>()
    private val productListAdapter by lazy {
        ProductListAdapter ({ product ->
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }, { recommend ->
            val bundle = Bundle()
            bundle.putSerializable("recommend", recommend)
            navController.navigate(R.id.action_listFragment_to_recommendDetailFragment, bundle)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initView()
        initScrollView()
    }

    fun initView() {
        with(viewDataBinding.rvProductList) {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }

        with(viewModel) {
            getProductResult(currentPage)
            getRecommendResult()

            productResponse.observe(viewLifecycleOwner, Observer { response ->
                productListAdapter.addProducts(response.productList, currentPage)
            })

            recommendResponse.observe(viewLifecycleOwner, Observer { response ->
                recommendLists.add(response.recommendList1)
                recommendLists.add(response.recommendList2)
                recommendLists.add(response.recommendList3)
                productListAdapter.addRecommends(recommendLists)
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