package com.example.cosmeticlistproject.ui.view.fragment

import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseFragment
import com.example.cosmeticlistproject.databinding.FragmentListBinding
import com.example.cosmeticlistproject.ui.viewmodel.ProductViewModel

class ListFragment: BaseFragment<FragmentListBinding, ProductViewModel>() {
    override val layoutResID: Int = R.layout.fragment_list
    override val viewModel: ProductViewModel = ProductViewModel()
}