package com.example.cosmeticlistproject.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmeticlistproject.R
import com.example.cosmeticlistproject.base.BaseActivity
import com.example.cosmeticlistproject.data.Brand
import com.example.cosmeticlistproject.data.ProductResponse
import com.example.cosmeticlistproject.data.Products
import com.example.cosmeticlistproject.data.RetrofitBuilder
import com.example.cosmeticlistproject.databinding.ActivityMainBinding
import com.example.cosmeticlistproject.ui.adapter.RecyclerviewAdapter
import com.example.cosmeticlistproject.ui.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MainViewModel = MainViewModel()

    lateinit var productList: List<Products>
    lateinit var brandList: List<Brand>
    val productAdapter by lazy {
        RecyclerviewAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.rvProductList.adapter = productAdapter
        viewDataBinding.rvProductList.layoutManager = LinearLayoutManager(applicationContext)
        getResult()

    }

    fun getResult() {
        RetrofitBuilder.api
            .getProductsList()
            .enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    val productResponse = response.body()
                    productList = productResponse!!.productsResult!!.productsList
                    brandList = productResponse!!.productsResult!!.brandList
                    Log.d("seolim", "$productList")
                    Log.d("seolim", "$brandList")


                    productAdapter.productList = productList
                    productAdapter.brandList = brandList
                    productAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }
}