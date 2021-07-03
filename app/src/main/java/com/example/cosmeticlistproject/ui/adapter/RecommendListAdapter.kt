package com.example.cosmeticlistproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmeticlistproject.databinding.ItemRecommendBinding
import com.example.cosmeticlistproject.ui.model.RecommendModel

class RecommendListAdapter(
    private val context: Context?,
    private val recommendList: List<RecommendModel>,
    val itemClick: (RecommendModel) -> Unit
) : RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder>() {

    inner class RecommendViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: RecommendModel) {
            binding.recommend = recommend

            itemView.setOnClickListener {
                itemClick(recommend)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendListAdapter.RecommendViewHolder = RecommendViewHolder(
        ItemRecommendBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecommendListAdapter.RecommendViewHolder, position: Int) {
        holder.bind(recommendList[position])
    }

    override fun getItemCount(): Int = recommendList.size
}

