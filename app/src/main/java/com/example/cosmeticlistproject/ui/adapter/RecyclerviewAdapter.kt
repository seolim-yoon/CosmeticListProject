package com.example.cosmeticlistproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.data.Brand
import com.example.cosmeticlistproject.data.Products
import com.example.cosmeticlistproject.databinding.ItemProductBinding

class RecyclerviewAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerviewAdapter.ResultViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerviewAdapter.ResultViewHolder  = ResultViewHolder(ItemProductBinding.inflate(
        LayoutInflater.from(context), parent, false))

    lateinit var productList: List<Products>
    lateinit var brandList: List<Brand>

    override fun onBindViewHolder(
        holder: RecyclerviewAdapter.ResultViewHolder,
        position: Int
    ) {
        holder.bind(productList[position], brandList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ResultViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products, brand: Brand) {
            binding.product = product
            binding.brand = brand
        }
    }

}