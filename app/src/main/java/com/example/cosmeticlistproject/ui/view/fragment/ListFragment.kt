package com.example.cosmeticlistproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.data.transformProductModel
import com.example.cosmeticlistproject.data.transformRecommendModelList
import com.example.cosmeticlistproject.databinding.FragmentListBinding
import com.example.cosmeticlistproject.ui.adapter.ProductListAdapter
import com.example.cosmeticlistproject.ui.model.BaseModel
import com.example.cosmeticlistproject.ui.model.DetailModel
import com.example.cosmeticlistproject.ui.model.ProductModel
import com.example.cosmeticlistproject.ui.model.RecommendModel
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel
import com.example.cosmeticlistproject.util.StateResult

class ListFragment: BaseFragment<FragmentListBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_list
    override val viewModel: ProductViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(ProductViewModel::class.java)
    }

    private var currentPage = 1

    private val productListAdapter by lazy {
        ProductListAdapter {
            val bundle = Bundle()
            detailModelMapper(it)?.let { detailModel ->
                bundle.putSerializable("detailModel", detailModel)
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initScrollView()
    }

    private fun initView() {
        with(viewDataBinding.rvProductList) {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = productListAdapter
        }

        with(viewModel) {
            productResponse.observe(viewLifecycleOwner, Observer { response ->
                if(!loadPage.contains(currentPage)) {
                    productListAdapter.addProducts(response.transformProductModel(), currentPage)
                }
            })

            recommendResponse.observe(viewLifecycleOwner, Observer { response ->
                productListAdapter.addRecommends(response.transformRecommendModelList())
            })

            stateResult.observe(viewLifecycleOwner, Observer { state ->
                if(state == StateResult.ERROR)
                    productListAdapter.deleteLoading()
            })
        }
    }

    private fun initScrollView() {
        viewDataBinding.rvProductList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!viewDataBinding.rvProductList.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    if (viewModel.stateResult.value == StateResult.SUCCESS) {
                        productListAdapter.deleteLoading()
                        viewModel.getProductResult(++currentPage)
                    }
                }
            }
        })
    }

    private fun detailModelMapper(model : BaseModel) = when(model) {
        is ProductModel -> {
            DetailModel(model.imageUrl, model.productTitle)
        }
        is RecommendModel -> {
            DetailModel(model.imageUrl, model.productTitle)
        }
        else -> null
    }
}