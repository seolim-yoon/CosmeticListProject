package com.example.cosmeticlistproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.data.Product
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.FragmentRecommendDetailBinding
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class RecommendDetailFragment: BaseFragment<FragmentRecommendDetailBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_recommend_detail
    override val viewModel: ProductViewModel = ProductViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        viewDataBinding.recommend = arguments?.getSerializable("recommend") as Recommend
    }
}