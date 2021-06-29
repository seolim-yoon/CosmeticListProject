package com.example.cosmeticlistproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.data.Products
import com.example.cosmeticlistproject.databinding.ItemProductBinding

class ProductListAdapter(private val context: Context?,
                         val productList: List<Products>) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ProductViewHolder  = ProductViewHolder(ItemProductBinding.inflate(
        LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(
        holder: ProductListAdapter.ProductViewHolder,
        position: Int
    ) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            binding.product = product
        }
    }
}