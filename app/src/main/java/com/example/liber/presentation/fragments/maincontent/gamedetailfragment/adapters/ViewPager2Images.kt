package com.example.liber.presentation.fragments.maincontent.gamedetailfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.liber.databinding.ViewpagerImageItemBinding
import com.example.liber.domain.model.ShortScreenshot

class ViewPager2Images : ListAdapter<ShortScreenshot, ViewPager2Images.ImagesViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<ShortScreenshot>() {
            override fun areItemsTheSame(oldItem: ShortScreenshot, newItem: ShortScreenshot)
                = oldItem::class == newItem::class
            override fun areContentsTheSame(oldItem: ShortScreenshot, newItem: ShortScreenshot)
                = oldItem == newItem
        }
    }

    inner class ImagesViewHolder(val binding : ViewpagerImageItemBinding) : ViewHolder(binding.root) {
        fun bind(imagePath: ShortScreenshot) {
            Glide.with(itemView).load(imagePath.image).into(binding.imageProductDetails)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            ViewpagerImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }
}