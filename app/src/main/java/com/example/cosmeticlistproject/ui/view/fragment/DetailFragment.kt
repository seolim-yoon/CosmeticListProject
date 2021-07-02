package com.example.cosmeticlistproject.ui.view.fragment

import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.databinding.FragmentDetailBinding
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class DetailFragment: BaseFragment<FragmentDetailBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_detail
    override val viewModel: ProductViewModel = ProductViewModel()
}