package com.example.cosmeticlistproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.databinding.ItemLoadingBinding
import com.example.cosmeticlistproject.databinding.ItemProductBinding
import com.example.cosmeticlistproject.databinding.ItemRecommendListBinding
import com.example.cosmeticlistproject.ui.model.*

class ProductListAdapter(val itemClick: (BaseModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_RECOMMEND = 1
    private val VIEW_TYPE_LOADING = 2

    private var modelList: ArrayList<BaseModel> = arrayListOf()
    private var recommendList: ArrayList<ArrayList<RecommendModel>> = arrayListOf()

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
            holder.bind(modelList[position] as ProductModel)
        } else if (holder is RecommendListViewHolder) {
            holder.bind(modelList[position] as RecommendListModel)
        }
    }

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int {
        return when (modelList[position]) {
            is ProductModel -> VIEW_TYPE_ITEM
            is RecommendListModel -> VIEW_TYPE_RECOMMEND
            is LoadingModel -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.product = product

            itemView.setOnClickListener {
                itemClick(product)
            }
        }
    }

    inner class RecommendListViewHolder(private val binding: ItemRecommendListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendListModel: RecommendListModel) {
            with(binding.rvRecommendList) {
                adapter = RecommendListAdapter(context, recommendListModel.recommendModels) {
                    itemClick(it)
                }
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    fun addProducts(products: List<ProductModel>, page: Int) {
        modelList.addAll(products)
        when (page) {
            1 -> {
                modelList.add(10, RecommendListModel(recommendList[0]))
                modelList.add(RecommendListModel(recommendList[1]))
            }
            2 -> {
                modelList.add(32, RecommendListModel(recommendList[2]))
            }
        }
        modelList.add(LoadingModel())
        notifyDataSetChanged()
    }

    fun addRecommends(recommends: ArrayList<ArrayList<RecommendModel>>) {
        recommendList.addAll(recommends)
    }

    fun deleteLoading() {
        modelList.removeAt(modelList.lastIndex)
    }
}