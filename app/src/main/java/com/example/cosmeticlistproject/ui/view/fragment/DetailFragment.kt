package com.example.cosmeticlistproject.ui.view.fragment

import android.os.Bundle
import android.view.View
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.databinding.FragmentDetailBinding
import com.example.cosmeticlistproject.ui.model.DetailModel
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class DetailFragment: BaseFragment<FragmentDetailBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_detail
    override val viewModel: ProductViewModel = ProductViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        viewDataBinding.model = arguments?.getSerializable("detailModel") as DetailModel
    }
}