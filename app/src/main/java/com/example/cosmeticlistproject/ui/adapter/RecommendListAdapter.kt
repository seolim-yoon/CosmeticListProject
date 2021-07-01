package com.example.cosmeticlistproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.data.Recommend
import com.example.cosmeticlistproject.databinding.ItemRecommendBinding

class RecommendListAdapter(private val context: Context?,
                    val recommendList: List<Recommend>)
    : RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder>()  {

    inner class RecommendViewHolder(private val binding: ItemRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: Recommend) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendListAdapter.RecommendViewHolder
    = RecommendViewHolder(
        ItemRecommendBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecommendListAdapter.RecommendViewHolder, position: Int) {
        if(holder is RecommendListAdapter.RecommendViewHolder){
            holder.bind(recommendList[position])
        }
    }

    override fun getItemCount(): Int = recommendList.size

}

