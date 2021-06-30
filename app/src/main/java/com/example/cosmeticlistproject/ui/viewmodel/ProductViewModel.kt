package com.example.cosmeticlistproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cosmeticlistproject.base.BaseViewModel
import com.example.cosmeticlistproject.data.ProductsResult
import com.example.cosmeticlistproject.repository.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductViewModel: BaseViewModel() {
    private val productRepository = ProductRepository()

    var productResponse: MutableLiveData<ProductsResult> = MutableLiveData()

    fun getResult(page: Int) {
        addDisposable(
            productRepository.getResult(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    productResponse.value = result
                }, {
                    Log.e("seolim", "error : " + it.message.toString())
                })
        )
    }
}