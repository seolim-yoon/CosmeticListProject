package com.example.cosmeticlistproject.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.data.Brand
import com.example.cosmeticlistproject.data.Products
import com.example.cosmeticlistproject.databinding.ItemLoadingBinding
import com.example.cosmeticlistproject.databinding.ItemProductBinding

class ProductListAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var productList: ArrayList<Products> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                ProductViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ProductViewHolder){
            holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int = productList.size

    override fun getItemViewType(position: Int): Int {
        return when(productList[position].productRank) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            binding.product = product
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) { }

    fun addProducts(products: ArrayList<Products>) {
        productList.addAll(products)
        productList.add(Products(" ", " ", " ", " ", " ", Brand(" ")))
        notifyDataSetChanged()
    }

    fun deleteLoading() {
        productList.removeAt(productList.lastIndex)
    }
}