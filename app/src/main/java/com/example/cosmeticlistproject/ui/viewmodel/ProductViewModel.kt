package com.example.cosmeticlistproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cosmeticlistproject.base.BaseViewModel
import com.example.cosmeticlistproject.data.ProductResult
import com.example.cosmeticlistproject.repository.ProductRepository
import com.example.cosmeticlistproject.util.StateResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductViewModel: BaseViewModel() {
    private val productRepository = ProductRepository()

    var productResponse: MutableLiveData<ProductResult> = MutableLiveData()
    var responseResult: MutableLiveData<StateResult> = MutableLiveData()

    init {
        responseResult.value = StateResult.LOADING
    }

    fun getResult(page: Int) {
        addDisposable(
            productRepository.getProductResult(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    productResponse.value = result
                    responseResult.value = StateResult.LOADING
                }, {
                    Log.e("seolim", "error : " + it.message.toString())
                    responseResult.value = StateResult.ERROR
                })
        )
    }
}