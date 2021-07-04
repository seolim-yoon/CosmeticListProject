package com.example.cosmeticlistproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cosmeticlistproject.base.BaseViewModel
import com.example.cosmeticlistproject.data.ProductResult
import com.example.cosmeticlistproject.data.RecommendResult
import com.example.cosmeticlistproject.repository.ProductRepository
import com.example.cosmeticlistproject.util.StateResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductViewModel : BaseViewModel() {
    private val productRepository = ProductRepository()

    var productResponse: MutableLiveData<ProductResult> = MutableLiveData()
    var recommendResponse: MutableLiveData<RecommendResult> = MutableLiveData()
    var stateResult: MutableLiveData<StateResult> = MutableLiveData()

    var loadPage = mutableSetOf<Int>()

    init {
        getProductResult(1)
        getRecommendResult()
    }

    fun getProductResult(page: Int) {
        addDisposable(
            productRepository.getProductResult(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    productResponse.value = result
                    stateResult.value = StateResult.SUCCESS
                    loadPage.add(page)
                }, {
                    stateResult.value = StateResult.ERROR
                    Log.e("seolim", "error : " + it.message.toString())
                })
        )
    }

    fun getRecommendResult() {
        addDisposable(
            productRepository.getRecommendResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    recommendResponse.value = result
                    stateResult.value = StateResult.SUCCESS
                }, {
                    stateResult.value = StateResult.ERROR
                    Log.e("seolim", "error : " + it.message.toString())
                })
        )
    }
}