package com.example.cosmeticlistproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.data.Brand
import com.example.cosmeticlistproject.data.Product
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.ItemLoadingBinding
import com.example.cosmeticlistproject.databinding.ItemProductBinding
import com.example.cosmeticlistproject.databinding.ItemRecommendListBinding

class ProductListAdapter(val itemClick: (Product) -> Unit,
                         val recommendItemClick: (Recommend) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_RECOMMEND = 1
    private val VIEW_TYPE_LOADING = 2

    private var productList: ArrayList<Product> = arrayListOf()
    private var recommendList: ArrayList<ArrayList<Recommend>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                ProductViewHolder(binding)
            }
            VIEW_TYPE_RECOMMEND -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecommendListBinding.inflate(layoutInflater, parent, false)
                RecommendListViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.bind(productList[position])
        } else if (holder is RecommendListViewHolder) {
            holder.bind(recommendList[position / 10 - 1])
        }
    }

    override fun getItemCount(): Int = productList.size

    override fun getItemViewType(position: Int): Int {
        return when (productList[position].productRank) {
            " " -> VIEW_TYPE_LOADING
            "R" -> VIEW_TYPE_RECOMMEND
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product

            itemView.setOnClickListener {
                itemClick(product)
            }
        }
    }

    inner class RecommendListViewHolder(private val binding: ItemRecommendListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendList: ArrayList<Recommend>) {
            with(binding.rvRecommendList) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = RecommendListAdapter(context, recommendList) {
                    recommendItemClick(it)
                }
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    fun addProducts(products: ArrayList<Product>, page: Int) {
        productList.addAll(products)
        when (page) {
            1 -> {
                productList.add(10, Product("R", " ", " ", " ", " ", Brand(" ")))
                productList.add(Product("R", " ", " ", " ", " ", Brand(" ")))
            }
            2 -> {
                productList.add(32, Product("R", " ", " ", " ", " ", Brand(" ")))
            }
        }
        productList.add(Product(" ", " ", " ", " ", " ", Brand(" ")))
        notifyDataSetChanged()
    }

    fun addRecommends(recommends: ArrayList<ArrayList<Recommend>>) {
        recommendList.addAll(recommends)
    }

    fun deleteLoading() {
        productList.removeAt(productList.lastIndex)
    }
}