package com.example.liber.presentation.fragments.maincontent.gamedetailfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.liber.databinding.PlatformRvItemBinding
import com.example.liber.domain.model.ParentPlatform

class PlatformsAdapter : ListAdapter<ParentPlatform, PlatformsAdapter.PlatformViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<ParentPlatform>() {
            override fun areItemsTheSame(
                oldItem: ParentPlatform,
                newItem: ParentPlatform) = oldItem::class == newItem::class

            override fun areContentsTheSame(
                oldItem: ParentPlatform,
                newItem: ParentPlatform) = oldItem.platform.id == newItem.platform.id
        }
    }

    inner class PlatformViewHolder(val binding: PlatformRvItemBinding) : ViewHolder(binding.root){
        fun bind(platformName: ParentPlatform) {
            binding.tvPlatformName.text = platformName.platform.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        return PlatformViewHolder(
            PlatformRvItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        val platformName = getItem(position)
        holder.bind(platformName)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, holder.binding.tvPlatformName.toString(), Toast.LENGTH_LONG)
            notifyItemChanged(position)
        }
    }
}